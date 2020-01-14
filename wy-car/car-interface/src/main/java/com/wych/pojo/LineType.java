package com.wych.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
@Data
@Table(name = "line")
public class LineType {
    private Integer id;
    @Column(name = "line_name")
    private String lineName;
}
