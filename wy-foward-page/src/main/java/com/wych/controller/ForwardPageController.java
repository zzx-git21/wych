package com.wych.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foreground")
public class ForwardPageController {

    @GetMapping("/goodSource/index")
    public String publishGoods(){
        System.out.println ("true = " + true);
        return "publishGoods";
    }

    @GetMapping("/member/index")
    public String memberIndex(String type){
        System.out.println ("type = " + type);
        return "publishGoods";
    }

}
