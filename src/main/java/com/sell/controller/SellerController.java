package com.sell.controller;

import com.sell.dto.OrderDTO;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){

        PageRequest pageRequest=PageRequest.of(page-1,size);
        Page<OrderDTO> orderDTOPage=orderService.findAll(pageRequest);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        ModelAndView modelAndView =  new ModelAndView ("order/list",map);
        return modelAndView;
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try{
            OrderDTO orderDTO=orderService.findOne(orderId);

            orderService.cancelOrder(orderDTO);
        }catch (SellException e){
            log.error("【卖家取消订单】错误，查询不到买家订单");
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");

        return new ModelAndView("common/success",map);

    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO=new OrderDTO();
        try{
         orderDTO=orderService.findOne(orderId);}
            catch(SellException e){
                log.error("【卖家取消订单】错误，查询不到买家订单");
                map.put("msg",e.getMessage());
                map.put("url","/sell/seller/order/list");
                return new ModelAndView("/common/error",map);
        }
        map.put("OrderDTO",orderDTO);
        return new ModelAndView("order/detail",map);

    }


}
