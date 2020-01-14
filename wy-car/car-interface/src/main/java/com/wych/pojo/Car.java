package com.wych.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "tb_car")
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    //出发地
    @Column(name = "start_address")
    private String startAddress;
    //目的地
    @Column(name = "end_address")
    private String endAddress;
    //车辆类型
    @Column(name = "car_type")
    private Integer carType;
   @Transient
   private String carTypeName;
   @Transient
   private String lineTypeName;
    //车辆载重
    @Column(name = "car_weight")
    private Double carWeight;
    //价格
    private Double price;
    //线路类型
    @Column(name = "line_type")
    private Integer lineType;
   /* //联系人
    @Column(name = "contact_id")
    private Long contactId;*/
    //出发日期
    @JsonFormat(pattern = "yyyy-MM-dd")
     @Column(name = "depart_time")
     private Date departTime;
     //备注
     @Column(name = "remark")
     private String remark;

    @Column(name = "phone")
    private Long phone;
    @Column(name = "name")
    private String name;

}
