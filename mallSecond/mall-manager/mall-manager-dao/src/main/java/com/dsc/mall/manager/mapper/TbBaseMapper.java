package com.dsc.mall.manager.mapper;

import com.dsc.mall.manager.pojo.TbBase;
import com.dsc.mall.manager.pojo.TbBaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mapper
 * @author dsc
 *
 */
public interface TbBaseMapper {
    long countByExample(TbBaseExample example);

    int deleteByExample(TbBaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBase record);

    int insertSelective(TbBase record);

    List<TbBase> selectByExample(TbBaseExample example);

    TbBase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBase record, @Param("example") TbBaseExample example);

    int updateByExample(@Param("record") TbBase record, @Param("example") TbBaseExample example);

    int updateByPrimaryKeySelective(TbBase record);

    int updateByPrimaryKey(TbBase record);
}
