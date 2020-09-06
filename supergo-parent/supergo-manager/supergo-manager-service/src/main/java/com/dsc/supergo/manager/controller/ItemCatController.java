package com.dsc.supergo.manager.controller;


import com.dsc.supergo.manager.service.ItemCatService;
import com.dsc.supergo.pojo.Itemcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/cat/category")
    public List<Itemcat> getItemCatList() {
        return itemCatService.getItemCatList();
    }

    @RequestMapping("/itemcat/category/{categoryId}")
    public Itemcat getItemCatById(@PathVariable long categoryId) {
        Itemcat itemcat = itemCatService.findOne(categoryId);
        return itemcat;
    }

}
