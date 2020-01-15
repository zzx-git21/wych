package com.wych.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_complain")
public class Complain {

    @Id
    private Long id;
    private String complaintsReasons;
    private String complaintsPhoto;
    private Long userId;
    private String username;//投诉人名字
    private Integer result;
    private Date complainTime;


}
