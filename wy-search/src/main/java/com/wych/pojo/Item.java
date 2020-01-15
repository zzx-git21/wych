package com.wych.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

@Data
@Document(indexName = "item", type = "docs", shards = 3, replicas = 1)
public class Item {

    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String goodsName;
    @Field(type = FieldType.Keyword, index = false)
    private String goodsType;
    private String goodsInit;
    private String goodsInitDetail;
    private String goodsDest;
    private String goodsDestDetail;
    private String contactName;
    private String contactPhone;
    private String goodsNum;
    private Long goodsWeight;
    private Long goodsCost;
    private String departureTime;
    private String remark;
    private String selectedGoodsWeight;


}
