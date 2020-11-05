package com.dsc.search.serviceImpl;

import com.dsc.mall.manager.dto.EsInfo;
import com.dsc.search.mapper.ItemMapper;
import com.dsc.search.search.SearchItemService;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public EsInfo getEsInfo() {
        return null;
    }
}
