package com.dsc.content.service.impl;

import com.dsc.common.jedis.JedisClient;
import com.dsc.common.pojo.DataTablesResult;
import com.dsc.content.ContentService;
import com.dsc.mall.manager.dto.front.AllGoodsResult;
import com.dsc.mall.manager.dto.front.ProductDet;
import com.dsc.mall.manager.mapper.TbItemDescMapper;
import com.dsc.mall.manager.mapper.TbItemMapper;
import com.dsc.mall.manager.mapper.TbPanelContentMapper;
import com.dsc.mall.manager.mapper.TbPanelMapper;
import com.dsc.mall.manager.pojo.TbPanel;
import com.dsc.mall.manager.pojo.TbPanelContent;
import com.github.pagehelper.PageHelper;
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
 *@author dsc
 */
public class ContentServiceImpl implements ContentService {

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

    @Value("PRODUCT_HOME")
    private String PRODUCT_HOME;

    @Value("PRODUCT_ITEM")
    private String PRODUCT_ITEM;

    @Value("6")
    private Integer RECOMEED_PANEL_ID;

    @Value("4")
    private Integer THANK_PANEL_ID;

    @Value("RECOMEED_PANEL")
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
        return 0;
    }

    @Override
    public DataTablesResult getPanelContentListByPanelId(int panelId) {
        return null;
    }

    @Override
    public int deletePanelContent(int id) {
        return 0;
    }

    @Override
    public int updateContent(TbPanelContent tbPanelContent) {
        return 0;
    }

    @Override
    public TbPanelContent getTbPanelContentById(int id) {
        return null;
    }

    @Override
    public List<TbPanel> getHome() {
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
