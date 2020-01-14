package com.wych.controller;

import com.wych.common.vo.PageResult;
import com.wych.pojo.Appeal;
import com.wych.pojo.Complain;
import com.wych.service.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppealController {

    @Autowired
    private AppealService appealService;



    //申述分页查询
    @GetMapping("/list/status")
    public ResponseEntity<PageResult<Appeal>> selectAppeal(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                           @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                                                           @RequestParam("userId") Long userId){

        PageResult<Appeal> appeals=appealService.selectAppeal(page,rows,userId);
        return ResponseEntity.ok(appeals);


    }

    //投诉分页查询
    @GetMapping("/list")
    public ResponseEntity<PageResult<Complain>> selectComplain(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                           @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                                                           @RequestParam("userId") Long userId){

        PageResult<Complain> appeals=appealService.selectComplain(page,rows,userId);
        return ResponseEntity.ok(appeals);


    }



    //添加申诉

    @GetMapping("/select/complain")
    public ResponseEntity<List<Complain>> select(String username){
        //第一步回显投诉信息
       List<Complain> complain= appealService.selecetComplain(username);
       return ResponseEntity.ok(complain);
    }
    //正式添加
    @PostMapping("/insert/appeal")
    public ResponseEntity<Void> insert(Appeal appeal){
        appealService.insert(appeal);
        return ResponseEntity.ok(null);

    }

    //添加投诉
    //正式添加
    @PostMapping("/insert/complain")
    public ResponseEntity<Void> insertComplain(Complain complain){
        appealService.insertComplain(complain);
        return ResponseEntity.ok(null);

    }

    //删除投诉
    @DeleteMapping("delete/complain")
    public ResponseEntity<Void> deleteComplain(Long id){
        appealService.deleteComplain(id);
        return ResponseEntity.ok(null);
    }


}
