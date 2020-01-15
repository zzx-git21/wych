package com.wych.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Table(name = "tb_user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String phone;
    @JsonIgnore
    @Length(message = "密码只能在4~30位之间", min = 4, max =30 )
    private String  password;
    @Length(message = "用户名只能在4~30位之间", min = 4, max =30 )
    private String username;
    private  String email;
    private String company;
    private String referrerPhone;//推荐人手机号
    private  Integer companyId;
    private  String roleId;
    @JsonFormat(pattern = "yyyy-MM-dd  hh:mm:ss")
    private Date createTime;

    private String image;
    private String identityCard;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String sex;
    private Integer age;
    private String wechat;
    private String qq;
    @JsonIgnore
    private String salt;// 密码的盐值




}
