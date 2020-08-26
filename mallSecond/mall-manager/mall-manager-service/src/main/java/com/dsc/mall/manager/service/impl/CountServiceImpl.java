package com.dsc.mall.manager.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.dsc.common.constant.CountConstant;
import com.dsc.common.utils.TimeUtil;
import com.dsc.mall.manager.dto.OrderChartData;
import com.dsc.mall.manager.mapper.TbOrderMapper;
import com.dsc.mall.mapper.service.CountService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * sevice实现类
 * @author 60221
 */
public class CountServiceImpl implements CountService {
    private final static Logger log= LoggerFactory.getLogger(CountServiceImpl.class);
    /**
     *方法实现
     * @param type
     * @param startTime
     * @param endTime
     * @param year
     * @return
     */
    @Autowired
    TbOrderMapper tbOrderMapper;

    @Override
    public List<OrderChartData> getOrderCountData(int type, Date startTime, Date endTime, int year) {

        List<OrderChartData> fullData=new ArrayList<>();
        if(type == CountConstant.THIS_WEEK){
            //本周
            List<OrderChartData> data = tbOrderMapper.selectOrderChart(TimeUtil.getBeginDayOfWeek(),TimeUtil.getEndDayOfWeek());
            fullData = getFullData(data,TimeUtil.getBeginDayOfWeek(),TimeUtil.getEndDayOfWeek());
        }else if(type == CountConstant.THIS_MONTH){
            //本月
            List<OrderChartData> data = tbOrderMapper.selectOrderChart(TimeUtil.getBeginDayOfMonth(),TimeUtil.getEndDayOfMonth());
            fullData = getFullData(data,TimeUtil.getBeginDayOfMonth(),TimeUtil.getEndDayOfMonth());
        }else if(type == CountConstant.LAST_MONTH){
            //上个月
            List<OrderChartData> data = tbOrderMapper.selectOrderChart(TimeUtil.getBeginDayOfLastMonth(), TimeUtil.getEndDayOfLastMonth());
            fullData = getFullData(data,TimeUtil.getBeginDayOfLastMonth(),TimeUtil.getEndDayOfLastMonth());
        }else if(type == CountConstant.CUSTOM_DATE){
            //自定义
            List<OrderChartData> data = tbOrderMapper.selectOrderChart(startTime, endTime);
            fullData = getFullData(data,startTime, endTime);
        }else if(type == CountConstant.CUSTOM_YEAR){
            List<OrderChartData> data = tbOrderMapper.selectOrderChartByYear(year);
            fullData = getFullYearData(data,year);
        }
        return fullData;
    }

    /**
     *  获取全年数据
     * @param data
     * @param year
     * @return
     */
    private List<OrderChartData> getFullYearData(List<OrderChartData> data, int year) {
        List<OrderChartData> fullData = new ArrayList<>();
        //起始月份
        Date everyMonth = TimeUtil.getBeginDayOfYear(year);
        int count = -1;
        for(int i=0;i<12;i++){
            boolean flag = true;
            for(OrderChartData chartData:data){
                if(DateUtil.month(chartData.getTime())==DateUtil.month(everyMonth)){
                    //有数据
                    flag = false;
                    count++;
                    break;
                }
            }
            if(!flag){
                fullData.add(data.get(count));
            }else{
                OrderChartData orderChartData = new OrderChartData();
                orderChartData.setTime(everyMonth);
                orderChartData.setMoney(new BigDecimal("0"));
                fullData.add(orderChartData);
            }

            //时间+1天
            Calendar cal = Calendar.getInstance();
            cal.setTime(everyMonth);
            cal.add(Calendar.MONTH, 1);
            everyMonth = cal.getTime();
        }
        return fullData;
    }

    /**
     * 获取全部数据
     * @param data
     * @param startTime
     * @param endTime
     * @return
     */
    private List<OrderChartData> getFullData(List<OrderChartData> data, Date startTime, Date endTime) {

        List<OrderChartData> fullData = new ArrayList<>();
        //相差
        long betweenDay = DateUtil.between(startTime, endTime, DateUnit.DAY);
        //起始时间
        Date everyday = startTime;
        int count = -1;
        for(int i=0;i<=betweenDay;i++){
            boolean flag = true;
            for(OrderChartData chartData:data){
                if(DateUtils.isSameDay(chartData.getTime(),everyday)){
                    //有数据
                    flag = false;
                    count++;
                    break;
                }
            }
            if(!flag){
                fullData.add(data.get(count));
            }else{
                OrderChartData orderChartData = new OrderChartData();
                orderChartData.setTime(everyday);
                orderChartData.setMoney(new BigDecimal("0"));
                fullData.add(orderChartData);
            }

            //时间+1天
            Calendar cal = Calendar.getInstance();
            cal.setTime(everyday);
            cal.add(Calendar.DAY_OF_MONTH, 1);
            everyday = cal.getTime();
        }
        return fullData;
    }
    }

