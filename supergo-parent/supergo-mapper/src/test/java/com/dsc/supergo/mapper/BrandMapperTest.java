package com.dsc.supergo.mapper;

import com.dsc.supergo.MapperApplication;
import com.dsc.supergo.pojo.Brand;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class BrandMapperTest extends TestCase {
    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void testFindById() {
        Brand brand = brandMapper.selectByPrimaryKey(1);
        System.out.println(brand);
    }

    @Test
    public void testFindByBrand() {
        Brand query = new Brand();
        query.setName("小米");
        List<Brand> list = brandMapper.select(query);
        list.forEach(System.out :: println);
    }

    @Test
    public void testFindByExample() {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "长虹");
        criteria.andEqualTo("firstChar", "C");
        List<Brand> list = brandMapper.selectByExample(example);
        list.forEach(System.out :: println);
    }

}
