package cn.dsc.content.service;

import com.dsc.common.execetion.MallException;
import com.dsc.common.jedis.JedisClient;
import com.dsc.common.pojo.DataTablesResult;
import com.dsc.mall.manager.dto.front.AllGoodsResult;
import com.dsc.mall.manager.dto.front.ProductDet;
import com.dsc.mall.manager.mapper.TbItemDescMapper;
import com.dsc.mall.manager.mapper.TbItemMapper;
import com.dsc.mall.manager.mapper.TbPanelContentMapper;
import com.dsc.mall.manager.mapper.TbPanelMapper;
import com.dsc.mall.manager.pojo.TbItem;
import com.dsc.mall.manager.pojo.TbPanel;
import com.dsc.mall.manager.pojo.TbPanelContent;
import com.dsc.mall.manager.pojo.TbPanelContentExample;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容实现类
 * @author 60221
 */
public class ContentServiceImpl implements ContentService{
    private final static Logger log= LoggerFactory.getLogger(PanelServiceImpl.class);

    @Autowired
    private TbPanelMapper tbPanelMapper;
    @Autowired
    private TbPanelContentMapper tbPanelContentMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${PRODUCT_HOME}")
    private String PRODUCT_HOME;

    @Value("${PRODUCT_ITEM}")
    private String PRODUCT_ITEM;

    @Value("${RECOMEED_PANEL_ID}")
    private Integer RECOMEED_PANEL_ID;

    @Value("${THANK_PANEL_ID}")
    private Integer THANK_PANEL_ID;

    @Value("${RECOMEED_PANEL}")
    private String RECOMEED_PANEL;

    @Value("${THANK_PANEL}")
    private String THANK_PANEL;

    @Value("${ITEM_EXPIRE}")
    private int ITEM_EXPIRE;

    @Value("${HEADER_PANEL_ID}")
    private int HEADER_PANEL_ID;

    @Value("${HEADER_PANEL}")
    private String HEADER_PANEL;
    @Override
    public int addPanelContent(TbPanelContent tbPanelContent) {
        tbPanelContent.setCreated(new Date());
        tbPanelContent.setUpdated(new Date());
        if (tbPanelContentMapper.insert(tbPanelContent)!=1){
            throw new MallException("添加首页板块内容失败");
        }
        //同步导航栏缓存
        if(tbPanelContent.getPanelId()==HEADER_PANEL_ID){
            updateIndexRedis();
        }
        //同步缓存
        deleteHomeRedis();
        return 1;
    }

    @Override
    public DataTablesResult getPanelContentListByPanelId(int panelId) {
        DataTablesResult result=new DataTablesResult();
        List<TbPanelContent> list=new ArrayList<>();

        TbPanelContentExample example=new TbPanelContentExample();
        TbPanelContentExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andPanelIdEqualTo(panelId);
        list=tbPanelContentMapper.selectByExample(example);
        for(TbPanelContent content:list){
            if(content.getProductId()!=null){
                TbItem tbItem=tbItemMapper.selectByPrimaryKey(content.getProductId());
                content.setProductName(tbItem.getTitle());
                content.setSalePrice(tbItem.getPrice());
                content.setSubTitle(tbItem.getSellPoint());
            }
        }

        result.setData(list);
        return result;
    }

    @Override
    public int deletePanelContent(int id) {
        if(tbPanelContentMapper.deleteByPrimaryKey(id)!=1){
            throw new MallException("删除首页板块失败");
        }
        //同步导航栏缓存
        if(id==HEADER_PANEL_ID){
            updateNavListRedis();
        }
        //同步缓存
        deleteHomeRedis();
        return 1;
    }
    /**
     * 同步首页缓存
     */
    public void deleteHomeRedis(){
        try {
            jedisClient.del(PRODUCT_HOME);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateNavListRedis() {

        try{
            jedisClient.del(HEADER_PANEL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int updateContent(TbPanelContent tbPanelContent) {
        TbPanelContent old=getTbPanelContentById(tbPanelContent.getId());
        if(StringUtils.isBlank(tbPanelContent.getPicUrl())){
            tbPanelContent.setPicUrl(old.getPicUrl());
        }
        if(StringUtils.isBlank(tbPanelContent.getPicUrl2())){
            tbPanelContent.setPicUrl2(old.getPicUrl2());
        }
        if(StringUtils.isBlank(tbPanelContent.getPicUrl3())){
            tbPanelContent.setPicUrl3(old.getPicUrl3());
        }
        tbPanelContent.setCreated(old.getCreated());
        tbPanelContent.setUpdated(new Date());
        if(tbPanelContentMapper.updateByPrimaryKey(tbPanelContent)!=1){
            throw new MallException("更新板块内容失败");
        }
        //同步导航栏缓存
        if(tbPanelContent.getPanelId()==HEADER_PANEL_ID){
            updateNavListRedis();
        }
        //同步缓存
        deleteHomeRedis();
        return 1;
    }

    @Override
    public TbPanelContent getTbPanelContentById(int id)
    {
        TbPanelContent tbPanelContent=tbPanelContentMapper.selectByPrimaryKey(id);
        if (tbPanelContent==null){
            throw new MallException("通过id获取内容失败");
        }
        return tbPanelContent;
    }

    @Override
    public List<TbPanel> getHome() {
        List<TbPanel> list=new ArrayList<>();
        //查询缓存
        try{
            //有缓存则读取
            String json=jedisClient.get(PRODUCT_HOME);
            if (json!=null){
                list=new Gson().fromJson(json,new TypeToken<List<TbPanel>>(){}.getRawType();
                if (json!=null){
                    log.info("读取了首页缓存");
                    return list;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //没有缓存
        TbPanelContentExample example=new TbPanelContentExample();
        TbPanelContentExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andP

        return null;
    }

    @Override
    public List<TbPanel> getRecommendGoods() {
        return null;
    }

    @Override
    public List<TbPanel> getThankGoods() {
        return null;
    }

    @Override
    public ProductDet getProductDet(Long id) {
        return null;
    }

    @Override
    public AllGoodsResult getAllProduct(int page, int size, String sort, Long cid, int priceGt, int priceLte) {
        return null;
    }

    @Override
    public String getIndexRedis() {
        return null;
    }

    @Override
    public int updateIndexRedis() {
        return 0;
    }

    @Override
    public String getRecommendRedis() {
        return null;
    }

    @Override
    public int updateRecommendRedis() {
        return 0;
    }

    @Override
    public String getThankRedis() {
        return null;
    }

    @Override
    public int updateThankRedis() {
        return 0;
    }

    @Override
    public List<TbPanelContent> getNavList() {
        return null;
    }
}
