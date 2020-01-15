package com.wych.controller;

import com.wych.pojo.User;
import com.wych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/data")
    public ResponseEntity<Boolean> checkUserData(String data){
        System.out.println(data);


        boolean boo=userService.selectData(data);
        return ResponseEntity.ok(boo);
    }

    @PostMapping("/code")
    public ResponseEntity<Void> sendVerifyCode(@RequestBody String phone){
       String[] split = phone.split("=");
       String phone1=split[1];
        System.out.println(phone1);


        Boolean boo= userService.sendVerfyCode(phone1);
       return ResponseEntity.ok(null);
    }


    /**
     * 注册
     *
     * @param user
     * @param code
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid User user, @RequestParam("code") String code, HttpServletRequest request) {
        user.setCreateTime(new Date());
        this.userService.register(user, code);
        return ResponseEntity.ok(user);
    }

    //登录
    @GetMapping("/login")
    public ResponseEntity<User> queryUser(@RequestParam("phone") String phone,
                                          @RequestParam("password")String password){
        User user = this.userService.queryUser(phone, password);
        return ResponseEntity.ok(user);
    }


    //完善注册信息
    //第一步回显用户信息

    @GetMapping("/basics")
    public ResponseEntity<User> selecetUserInfo(@RequestParam("id") Long id){
       User user= userService.selecetUserInfo(id);
       return ResponseEntity.ok(user);
    }




    @PutMapping("/deatails")
    public ResponseEntity<Void> details(@RequestBody User user) {
        System.out.println(user.toString());
        userService.updateUser(user);
        return ResponseEntity.ok(null);
    }

    //申诉
    //第一步回显投诉信息



}
