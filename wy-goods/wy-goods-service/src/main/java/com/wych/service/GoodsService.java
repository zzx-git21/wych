package com.wych.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wych.common.enums.ExceptionEnums;
import com.wych.common.exception.WychException;
import com.wych.dto.PageResult;
import com.wych.mapper.ContactMapper;
import com.wych.mapper.GoodsMapper;
import com.wych.pojo.Contact;
import com.wych.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Transactional
    public void addGoodsResource(Goods goods) {

        int i = goodsMapper.insertSelective (goods);
         if (i<=0){
             throw new RuntimeException ();
         }
    }

    public PageResult<Goods> queryGoodsByPage(Integer page, Integer rows) {
        // 1、查询SPU
        // 分页,最多允许查100条
        PageHelper.startPage (page, Math.min (rows, 100));
        List<Goods> list = goodsMapper.selectAll ();
        if(CollectionUtils.isEmpty (list)){
            throw new WychException (ExceptionEnums.GOODS_NOT_FOUND);
        }
        PageInfo<Goods> pageInfo = new PageInfo<> (list);
        return new PageResult<> (pageInfo.getTotal (),Long.valueOf (pageInfo.getPages ()),list);
    }

    public PageResult<Goods> queryGoodsByCriteria(Integer page, Integer rows, Goods goods) {
        // 1、查询SPU
        // 分页,最多允许查100条
        PageHelper.startPage (page, Math.min (rows, 100));
        List<Goods> list = null;
        if(goods!=null){
            Example example = new Example (Goods.class);
            Example.Criteria criteria = example.createCriteria ();
            String goodsInit = goods.getGoodsInit();
            if(goodsInit !=null&&!goodsInit.equals("-1")){
                criteria.andLike ("goodsInit", goodsInit +"%");
            }
            String goodsType = goods.getGoodsType();
            if (goodsType !=null&&!goodsType.equals("-1")){
                criteria.andLike ("goodsType", goodsType +"%");
            }
            String goodsDest = goods.getGoodsDest();
            if (goodsDest !=null&&!goodsDest.equals("-1")){
                criteria.andLike ("goodsDest", goodsDest +"%");
            }
            Long minGoodsCost = goods.getMinGoodsCost();
            Long maxGoodsCost = goods.getMaxGoodsCost();
            if (minGoodsCost !=null && maxGoodsCost !=null&& !maxGoodsCost.equals("-1")&&!minGoodsCost.equals("-1")){
                criteria.andBetween ("goodsCost", minGoodsCost, maxGoodsCost);
            }
            if (goods.getMinGoodsWeight()!=null&& goods.getMaxGoodsWeight()!=null){
                criteria.andBetween ("goodsWeight",goods.getMinGoodsWeight (),goods.getMaxGoodsWeight ());
            }
            list = goodsMapper.selectByExample (example);
        }
        if(CollectionUtils.isEmpty (list)){
            throw new WychException (ExceptionEnums.GOODS_NOT_FOUND);
        }
        PageInfo<Goods> pageInfo = new PageInfo<> (list);
        return new PageResult<> (pageInfo.getTotal (),Long.valueOf (pageInfo.getPages ()),list);
    }
}

