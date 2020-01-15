package com.wych.pojo;
import lombok.Data;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "tb_appeal")
public class Appeal {

    @Id
    private Long id;
    private String proofPhoto;
    private String appealReason;
    private Date proofTime;
    private Long userId;
    private String username;
    private String phone;
    private String eamil;





}
