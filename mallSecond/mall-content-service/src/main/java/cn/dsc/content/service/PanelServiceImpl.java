package cn.dsc.content.service;

import com.dsc.common.execetion.MallException;
import com.dsc.common.jedis.JedisClient;
import com.dsc.common.pojo.ZtreeNode;
import com.dsc.mall.manager.mapper.TbPanelMapper;
import com.dsc.mall.manager.pojo.TbPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Service类
 * @author dsc
 */
public class PanelServiceImpl implements PanelService{
    private final static Logger log= LoggerFactory.getLogger(PanelServiceImpl.class);
    @Autowired
    private TbPanelMapper tbPanelMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("PRODUCT_HOME")
    private String PRODUCT_HOME;
    @Override
    public TbPanel getTbPanelById(int id) {
        TbPanel tbPanel=tbPanelMapper.selectByPrimaryKey(id);
        if (tbPanel==null){
            throw new MallException("通过id获取板块失败");
        }
        return tbPanel;
    }

    @Override
    public List<ZtreeNode> getPanelList(int position, boolean showAll) {
        return null;
    }

    @Override
    public int addPanel(TbPanel tbPanel) {
        return 0;
    }

    @Override
    public int updatePanel(TbPanel tbPanel) {
        return 0;
    }

    @Override
    public int deletePanel(int id) {
        return 0;
    }
}
