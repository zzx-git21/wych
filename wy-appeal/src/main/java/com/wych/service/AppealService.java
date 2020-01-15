package com.wych.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wych.common.vo.PageResult;
import com.wych.mapper.AppealMapper;
import com.wych.mapper.ComplainMapper;
import com.wych.pojo.Appeal;
import com.wych.pojo.Complain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealService {

    @Autowired
    private ComplainMapper complainMapper;


    //申诉分页查询
    @Autowired
    private AppealMapper appealMapper;


    public PageResult<Appeal> selectAppeal(Integer page, Integer rows, Long userId) {

        PageHelper.startPage(page, rows);

        Appeal appeal = new Appeal();
        appeal.setUserId(userId);
        List<Appeal> appeals = appealMapper.select(appeal);
        PageInfo<Appeal> pageInfo = new PageInfo<>(appeals);
        return new PageResult<>(pageInfo.getTotal(), appeals);


    }



    ////添加申述第一步回显
    public List<Complain> selecetComplain(String username) {


        Complain complain = new Complain();
        complain.setUsername(username);

        return complainMapper.selectByExample(complain);
    }

    //添加申述第二部添加

    public void insert(Appeal appeal) {

        appealMapper.insert(appeal);


    }


    //投诉分页查询
    public PageResult<Complain> selectComplain(Integer page, Integer rows, Long userId) {
        PageHelper.startPage(page, rows);

        Complain complain=new Complain();
        complain.setUserId(userId);
        List<Complain> complains = complainMapper.selectByExample(complain);
        PageInfo<Complain> pageInfo=new PageInfo<>(complains);
        return new PageResult<>(pageInfo.getTotal(),complains);
    }


    //添加投诉
    public void insertComplain(Complain complain) {
        complainMapper.insert(complain);
    }



    //删除投诉
    public void deleteComplain(Long id) {
        complainMapper.deleteByPrimaryKey(id);
    }
}
