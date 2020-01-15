package com.wych.service;

import com.wych.pojo.Goods;
import com.wych.pojo.Item;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    public Item buildItem(Goods goods) {
        Item item = new Item();
        item.setId(goods.getId());
        item.setGoodsType(goods.getGoodsType());
        item.setGoodsInit(goods.getGoodsInit());
        item.setGoodsInitDetail(goods.getGoodsInitDetail());
        item.setGoodsDest(goods.getGoodsDest());
        item.setGoodsDestDetail(goods.getGoodsDestDetail());
        item.setContactName(goods.getContactName());
        item.setContactPhone(goods.getContactPhone());
        item.setGoodsCost(goods.getGoodsCost());
        Long goodsWeight = goods.getGoodsWeight();
        item.setGoodsWeight(goodsWeight);
        item.setDepartureTime(goods.getDepartureTime());
        item.setRemark(goods.getRemark());
        if (goodsWeight==0||goodsWeight==null){
            item.setSelectedGoodsWeight("");
        } else if (goodsWeight < 30) {
            item.setSelectedGoodsWeight("30吨以下");
        } else if (goodsWeight < 300) {
            item.setSelectedGoodsWeight("30吨-300吨");
        } else if (goodsWeight < 1000) {
            item.setSelectedGoodsWeight("300-1000吨");
        } else if (goodsWeight < 2000) {
            item.setSelectedGoodsWeight("1000吨-2000吨");
        } else {
            item.setSelectedGoodsWeight("2000吨以上");
        }
        return item;
    }


}

