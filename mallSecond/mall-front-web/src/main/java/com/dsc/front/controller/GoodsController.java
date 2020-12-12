package com.dsc.front.controller;

import com.dsc.common.pojo.Result;
import com.dsc.common.utils.ResultUtil;
import com.dsc.content.ContentService;
import com.dsc.mall.manager.pojo.TbPanel;
import com.dsc.mall.manager.pojo.TbPanelContent;
import com.dsc.search.search.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dsc
 */
@RestController
@Api(description = "商品页展示")
public class GoodsController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/goods/navList",method = RequestMethod.GET)
    @ApiOperation(value = "获取导航栏")
    public Result<List<TbPanelContent>> getNavList(){
        List<TbPanelContent> list =contentService.getNavList();
        return new ResultUtil<List<TbPanelContent>>().setData(list);
    }
    @RequestMapping(value = "/goods/home",method = RequestMethod.GET)
    @ApiOperation(value = "首页内容展示")
    public Result<List<TbPanel>> getProductHome(){
        List<TbPanel> list = contentService.getHome();
        return new ResultUtil<List<TbPanel>>().setData(list);
    }

}
