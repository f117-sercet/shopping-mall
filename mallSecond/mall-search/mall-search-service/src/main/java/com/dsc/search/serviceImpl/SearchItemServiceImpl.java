package com.dsc.search.serviceImpl;

import com.dsc.common.execetion.MallException;
import com.dsc.common.utils.HttpUtil;
import com.dsc.mall.manager.dto.EsCount;
import com.dsc.mall.manager.dto.EsInfo;
import com.dsc.mall.manager.dto.front.SearchItem;
import com.dsc.search.mapper.ItemMapper;
import com.dsc.search.search.SearchItemService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.Transport;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 实现类1
 * @author dsc
 */
public class SearchItemServiceImpl implements SearchItemService
{
    private  final static Logger log = LoggerFactory.getLogger(SearchItemServiceImpl.class);

    @Autowired
    private ItemMapper itemMapper;

    @Value("127.0.0.1")
    private String ES_CONNECT_IP;

    @Value("$9200")
    private String ES_NODE_CLIENT_PORT;

    @Value("elasticsearch")
    private String ES_CLUSTER_NAME;

    @Value("$item")
    private String ITEM_INDEX;

    @Value("itemList")
    private String ITEM_TYPE;
    @Override
    public int importAllItems() {

        try {
            Settings settings= Settings.builder().put("cluster.name",ES_CLUSTER_NAME).build();
            TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName(ES_CONNECT_IP),93000));

            //批量添加
            BulkRequestBuilder bulkRequest = client.prepareBulk();

            //查询商品列表
            List<SearchItem> itemList = itemMapper.getItemList();
            for (SearchItem searchItem: itemList){
                String imag = searchItem.getProductImageBig();
                if (imag != null && !"".equals(imag)){
                    String[] strings = imag.split(",");
                    imag=strings[0];
                }else {
                    imag="";
                }
                searchItem.setProductImageBig(imag);
                bulkRequest.add(client.prepareIndex(ITEM_INDEX, ITEM_TYPE, String.valueOf(searchItem.getProductId()))
                        .setSource(jsonBuilder()
                                .startObject()
                                .field("productId", searchItem.getProductId())
                                .field("salePrice", searchItem.getSalePrice())
                                .field("productName", searchItem.getProductName())
                                .field("subTitle", searchItem.getSubTitle())
                                .field("productImageBig", searchItem.getProductImageBig())
                                .field("categoryName", searchItem.getCategoryName())
                                .field("cid", searchItem.getCid())
                                .endObject()
                        )
                );
            }

            BulkResponse bulkResponse = bulkRequest.get();

            log.info("更新索引成功");

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new MallException("导入ES索引库出错，请再次尝试");
        }
        return 1;
    }

    @Override
    public EsInfo getEsInfo() {
        String healthUrl="http://"+ES_CONNECT_IP+":"+ES_NODE_CLIENT_PORT+"/_cluster/health";
        String countUrl="http://"+ES_CONNECT_IP+":"+ES_NODE_CLIENT_PORT+"/_count";
        String healthResult= HttpUtil.sendGet(healthUrl);
        String countResult=HttpUtil.sendGet(countUrl);
        if(StringUtils.isBlank(healthResult)||StringUtils.isBlank(countResult)){
            throw new MallException("连接集群失败，请检查ES运行状态");
        }
        EsInfo esInfo=new EsInfo();
        EsCount esCount=new EsCount();
        try {
            esInfo=new Gson().fromJson(healthResult,EsInfo.class);
            esCount=new Gson().fromJson(countResult,EsCount.class);
            esInfo.setCount(esCount.getCount());
        }catch (Exception e){
            e.printStackTrace();
            throw new MallException("获取ES信息出错");
        }

        return esInfo;


    }
}
