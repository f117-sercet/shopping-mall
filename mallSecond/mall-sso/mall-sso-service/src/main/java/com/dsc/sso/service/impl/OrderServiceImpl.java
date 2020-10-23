package com.dsc.sso.service.impl;

import com.dsc.common.execetion.MallException;
import com.dsc.common.jedis.JedisClient;
import com.dsc.mall.manager.dto.DtoUtil;
import com.dsc.mall.manager.dto.front.CartProduct;
import com.dsc.mall.manager.dto.front.Order;
import com.dsc.mall.manager.dto.front.OrderInfo;
import com.dsc.mall.manager.dto.front.PageOrder;
import com.dsc.mall.manager.mapper.*;
import com.dsc.mall.manager.pojo.*;
import com.dsc.sso.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单Service实现类
 * @author 60221
 */
public class OrderServiceImpl implements OrderService {

    private final static Logger log= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    /**
     * 用户
     */
    private TbMemberMapper tbMemberMapper;
    @Autowired
    /**
     * 订单
     */
    private TbOrderMapper tbOrderMapper;
    @Autowired
    /**
     * 订单商品
     */
    private TbOrderItemMapper tbOrderItemMapper;
    /**
     * 订单物流
     */
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private TbThanksMapper tbThanksMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("1209600")
    private int PAY_EXPIRE;
    /**
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageOrder getOrderList(Long userId, int page, int size) {
        //分页
        if(page<=0) {
            page = 1;
        }
        PageHelper.startPage(page,size);

        PageOrder pageOrder=new PageOrder();
        List<Order> list=new ArrayList<>();

        TbOrderExample example=new TbOrderExample();
        TbOrderExample.Criteria criteria= example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("create_time DESC");
        List<TbOrder> listOrder =tbOrderMapper.selectByExample(example);
        for(TbOrder tbOrder:listOrder){

            judgeOrder(tbOrder);

            Order order=new Order();
            //orderId
            order.setOrderId(Long.valueOf(tbOrder.getOrderId()));
            //orderStatus
            order.setOrderStatus(String.valueOf(tbOrder.getStatus()));
            //createDate
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String date = formatter.format(tbOrder.getCreateTime());
            order.setCreateDate(date);
            //address
            TbOrderShipping tbOrderShipping=tbOrderShippingMapper.selectByPrimaryKey(tbOrder.getOrderId());
            TbAddress address=new TbAddress();
            address.setUserName(tbOrderShipping.getReceiverName());
            address.setStreetName(tbOrderShipping.getReceiverAddress());
            address.setTel(tbOrderShipping.getReceiverPhone());
            order.setAddressInfo(address);
            //orderTotal
            if(tbOrder.getPayment()==null){
                order.setOrderTotal(new BigDecimal(0));
            }else{
                order.setOrderTotal(tbOrder.getPayment());
            }
            //goodsList
            TbOrderItemExample exampleItem=new TbOrderItemExample();
            TbOrderItemExample.Criteria criteriaItem= exampleItem.createCriteria();
            criteriaItem.andOrderIdEqualTo(tbOrder.getOrderId());
            List<TbOrderItem> listItem =tbOrderItemMapper.selectByExample(exampleItem);
            List<CartProduct> listProduct=new ArrayList<>();
            for(TbOrderItem tbOrderItem:listItem){

                CartProduct cartProduct= DtoUtil.TbOrderItem2CartProduct(tbOrderItem);

                listProduct.add(cartProduct);
            }
            order.setGoodsList(listProduct);
            list.add(order);
        }
        PageInfo<Order> pageInfo=new PageInfo<>(list);
        pageOrder.setTotal(getMemberOrderCount(userId));
        pageOrder.setData(list);
        return pageOrder;
    }


    @Override
    public Order getOrder(Long orderId) {

        Order order=new Order();
        TbOrder tbOrder=tbOrderMapper.selectByPrimaryKey(String.valueOf(orderId));
        if (tbOrder==null){
            throw new MallException("通过id获取订单失败");
        }
        String validTime=judgeOrder(tbOrder);
        if (validTime!=null){
            order.setFinishDate(validTime);
        }
        //orderId
        order.setOrderId(Long.valueOf(tbOrder.getOrderId()));

        return null;
    }

    @Override
    public int cancelOrder(Long orderId) {
        return 0;
    }

    @Override
    public Long createOrder(OrderInfo orderInfo) {
        return null;
    }

    @Override
    public int delOrder(Long orderId) {
        return 0;
    }

    @Override
    public int payOrder(TbThanks tbThanks) {
        return 0;
    }

    /**
     * 判断订单是否超时未支付
     */
    public String judgeOrder(TbOrder tbOrder){

        String result=null;
        if(tbOrder.getStatus()==0){
            //判断是否已超1天
            long diff=System.currentTimeMillis()-tbOrder.getCreateTime().getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            if(days>=1){
                //设置失效
                tbOrder.setStatus(5);
                tbOrder.setCloseTime(new Date());
                if(tbOrderMapper.updateByPrimaryKey(tbOrder)!=1){
                    throw new MallException( "更新订单失效失败");
                }
            }else {
                //返回到期时间
                long time=tbOrder.getCreateTime().getTime()+1000 * 60 * 60 * 24;
                result= String.valueOf(time);
            }
        }
        return result;
    }

    public int getMemberOrderCount(Long userId){

        TbOrderExample example=new TbOrderExample();
        TbOrderExample.Criteria criteria= example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<TbOrder> listOrder =tbOrderMapper.selectByExample(example);
        if(listOrder!=null){
            return listOrder.size();
        }
        return 0;
    }
}

