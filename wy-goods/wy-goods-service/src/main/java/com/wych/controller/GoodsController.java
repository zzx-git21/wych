package com.wych.controller;

import com.wych.dto.PageResult;
import com.wych.pojo.Goods;
import com.wych.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 发布货源
     * @param goods
     * @param contact
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Void> addGoodsResource(Goods goods){
        this.goodsService.addGoodsResource(goods);
        return ResponseEntity.ok ().build ();
    }

    /**
     * 分页查询货源、根据货物名称搜索
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/page")
    public PageResult<Goods> queryGoodsByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows){
        PageResult<Goods> result = this.goodsService.queryGoodsByPage(page,rows);
        return result;
    }


    @GetMapping("/search")
    public ResponseEntity<PageResult<Goods>> queryGoodsByCriteria(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            Goods goods){
        PageResult<Goods> result = this.goodsService.queryGoodsByCriteria(page,rows,goods);
        return ResponseEntity.ok (result);
    }

}
