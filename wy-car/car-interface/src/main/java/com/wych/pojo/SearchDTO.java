package com.wych.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
   private Integer carTypeId;
   private String  endAddress;
   private String startAddress;
   private Integer lineType;
   private Integer price;
   private Integer carWeight;

   //最大价格
   private Double maxPrice;
   //最小价格
   private Double minPrice;
  //最大重量
   private Double maxWeight;
   //最小重量
   private Double minWeight;


}
