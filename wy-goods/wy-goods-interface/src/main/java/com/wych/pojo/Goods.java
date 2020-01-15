package com.wych.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "tb_goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goodsName;
    private String goodsType;
    private String goodsInit;
    private String goodsInitDetail;
    private String goodsDest;
    private String goodsDestDetail;
    private String contactName;
    private String contactPhone;
    private String goodsNum;
    private Long goodsWeight;
    @Transient
    @JsonIgnore
    private Long minGoodsWeight;
    @Transient
    @JsonIgnore
    private Long maxGoodsWeight;
    private Long goodsCost;
    @Transient
    @JsonIgnore
    private Long minGoodsCost;
    @Transient
    @JsonIgnore
    private Long maxGoodsCost;
    private String departureTime;
    @Transient
    @JsonIgnore
    private Date minDepartureTime;
    @Transient
    @JsonIgnore
    private Date maxDepartureTime;
    private String remark;

}
