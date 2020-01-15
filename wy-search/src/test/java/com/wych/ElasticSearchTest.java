package com.wych;

import com.wych.pojo.Goods;
import com.wych.pojo.Item;
import com.wych.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchSeverApplication.class)
public class ElasticSearchTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void createIndex(){
        // 创建索引库，以及映射
        this.template.createIndex(Item.class);
        this.template.putMapping(Item.class);
    }





}
