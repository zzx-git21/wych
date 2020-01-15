package com.wych;

import com.wych.client.ItemClient;
import com.wych.dto.PageResult;
import com.wych.pojo.Goods;
import com.wych.pojo.Item;
import com.wych.repository.ItemRepository;
import com.wych.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchSeverApplication.class)
public class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ItemClient itemClient;

    @Autowired
    private SearchService searchService;




    @Test
    public void createIndex(){
        // 创建索引库，以及映射
        this.template.createIndex(Item.class);
        this.template.putMapping(Item.class);
        Integer page = 1;
        Integer rows = 100;
        do {
            PageResult<Goods> pageResult = this.itemClient.queryGoodsByPage(page, rows);
            // 将goods集合变为item集合
            List<Item> itemList = pageResult.getItems().stream().map(goods ->
                this.searchService.buildItem(goods)
            ).collect(Collectors.toList());
            itemRepository.saveAll(itemList);
           // 获取当前页的数据条数，如果是最后一页，没有100条
            rows = pageResult.getItems().size();
            // 每次循环页码加1
            page++;
        }while (rows==100);





    }





}
