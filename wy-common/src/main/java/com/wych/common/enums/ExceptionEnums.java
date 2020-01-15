package com.wych.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {


    //1XX：分类错误

    //2XX：品牌错误
    BRAND_IS_EMPTY(200, "品牌为空！"),

    //4XX：商品错误
    PRICE_CANNOT_BE_NULL(400, "价格不能为空"),
    SPECIFICATION_GROUP_IS_EMPTY(401, "规格组为空！"),
    SPECIFICATION_GROUP_PARAM_IS_EMPTY(402, "该规格组对应的参数为空!"),
    GOODS_NOT_FOUND(403,"商品为空！"),
    GOODS_DETAIL_NOT_FOUND(404,"商品详情为空!"),
    SKU_IS_EMPTY(405,"sku信息为空！"),
    STOCK_NOT_ENOUGH(406,"库存不足！"),
    STOCK_NOT_FOUND(407, "未找到库存！"),

    //5xx:文件上传错误
    UPLOAD_FILE_ERROR_TYPE(500, "文件上传类型错误！"),
    UPLOAD_FILE_ERROR_CONTENT(501, "文件上传的内容不符合要求！"),
    UPLOAD_FILE_ERROR(502, "文件上传错误！"),

    //6xx:用户中心
    REGISTER_CODE_IS_ERROR(601, "验证码错误！"),
    INVALID_USERNAME_PASSWORD(602, "用户名或密码错误！"),
    PUBLICKEY_PARSER_ERROR(603,"公钥解析token失败！"),
    NO_AUTHORIZED(1002,"未授权用户！"),

    //7xx:订单中心
    ORDER_NOT_FOUND(701, "订单未发现"),
    ORDER_DETAIL_NOT_FOUNT(702, "订单详情未发现"),
    ORDER_STATUS_NOT_FOUND(703, "订单状态未有！"),
    CREATE_ORDER_ERROR(704,"创建订单失败！"),
    ORDER_STATUS_ERROE(705,"订单状态错误！"),
    WX_PAY_ORDER_FAIL(706, "微信订单支付失败"),
    INVALID_ORDER_PARAM(707, "非法订单参数"),
    UPDATE_ORDER_STATUS_ERROR(708,"订单状态更新失败！");
    private int code;
    private String msg;
}
