package com.wych.api;

import com.wych.dto.PageResult;
import com.wych.pojo.Goods;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface GoodsApi {

    @RequestMapping(name = "/page",method = RequestMethod.GET )
    PageResult<Goods> queryGoodsByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows);

}
