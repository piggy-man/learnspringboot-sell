package com.sell.controller;

import com.sell.VO.ResultVO;
import com.sell.converter.OrderForm2OrderDTO;
import com.sell.dto.OrderDTO;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.forms.OrderForm;
import com.sell.service.OrderService;
import com.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
@Autowired
    private OrderService orderService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors())
        {
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO= OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】错误，购物车为空,orderId={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult=orderService.createOrder(orderDTO);
        Map<String,String> map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);




    }
    //查看订单列表

    //查看订单详情

    //取消订单
}
