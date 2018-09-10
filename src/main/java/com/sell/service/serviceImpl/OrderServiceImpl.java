package com.sell.service.serviceImpl;

import com.sell.converter.OrderMaster2OrderDTO;
import com.sell.dataobject.OrderDetail;
import com.sell.dataobject.OrderMaster;
import com.sell.dataobject.ProductInfo;
import com.sell.dto.CartDTO;
import com.sell.dto.OrderDTO;
import com.sell.enums.OrderStatusEnum;
import com.sell.enums.PayStatusEnum;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.repository.OrderDetailRepository;
import com.sell.repository.OrderMasterRepository;
import com.sell.service.OrderService;
import com.sell.service.ProductService;
import com.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        //查询商品价格
        //遍历订单中的商品详情并查询出单价

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyUtil.KeyUniqueUtil();
        List<CartDTO> cartDTOList = new ArrayList<>();

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算出总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.KeyUniqueUtil());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
            //CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            //cartDTOList.add(cartDTO);
        }
        cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());

        //写入订单数据库（两张表OrderMaster OrderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(0);
        orderMaster.setOrderStatus(0);

        orderMasterRepository.save(orderMaster);

        //扣库存
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster = orderMasterRepository.findByOrderId(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findOrderList(String buyerId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenId(buyerId, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());


    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(OrderDTO orderDTO) {

        //查询订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确 orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //取消订单
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderDTO.getOrderId());
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMaster1 = orderMasterRepository.save(orderMaster);
        if (orderMaster1 == null) {
            log.error("【取消订单】更新失败，orderMaster={}", orderMaster1);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //回补库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
             /*List<CartDTO> cartDTOList=new ArrayList<>();
             for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
                 CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
                 cartDTOList.add(cartDTO);
             }*/
        productService.increaseStock(cartDTOList);
        //如果已经付款，进行退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }


    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态  写入orderMaster表
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderDTO.getOrderId());
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付成功】订单状态不正确 orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付成功】订单支付状态不正确，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderDTO.getOrderId());
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if (result == null) {
            log.error("【订单支付成功】支付更新状态失败,orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);

        }
        return orderDTO;
    }
}
