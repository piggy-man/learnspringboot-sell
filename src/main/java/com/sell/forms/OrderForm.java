package com.sell.forms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class OrderForm {

    @NotEmpty(message = "姓名不为空")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "openid不能为空")
    private String openid;

    @NotEmpty(message = "购物车信息不能为空")
    private String items;


}
