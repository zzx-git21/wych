package com.wych.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
@Data
@Table(name = "tb_cartype")
public class CarType {
   private Integer id;
   @Column(name = "car_type_name")
   private String carTypeName;
}
