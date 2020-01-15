package com.wych.client;

import com.wych.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("goods-server")
public interface ItemClient extends GoodsApi {

}
