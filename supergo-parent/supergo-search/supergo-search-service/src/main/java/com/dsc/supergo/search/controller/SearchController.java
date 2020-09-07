package com.dsc.supergo.search.controller;

import com.dsc.supergo.http.HttpResult;
import com.dsc.supergo.search.eneity.SearchResult;
import com.dsc.supergo.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/goods/import")
    public HttpResult goodsImport() {
        new Thread( ()-> searchService.importGoods())
                .start();
        return HttpResult.ok();
    }

    @RequestMapping("/goods/search")
    public SearchResult search(@RequestParam(required = true) String keyword,
                               @RequestParam(required = false, value = "ev") String filter,
                               @RequestParam(defaultValue = "1") int page ,
                               @RequestParam(defaultValue = "5") int size) {
        //filter的参数形式：ev=brand_name-小米|category3_id-255、
        Map<String, String> filterMap = null;
        try {
            if (StringUtils.isNotBlank(filter)) {
                String[] filters = filter.split("\\|");
                filterMap = Stream.of(filters).collect(Collectors.toMap(e -> e.split("-")[0].trim(), e -> e.split("-")[1].trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(filter);
        System.out.println(filterMap);
        SearchResult searchResult = searchService.search(keyword, filterMap, page, size);
        return searchResult;
    }

}
