<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--侧边栏-->
    <#include "../common/nav.ftl">
    <#--内容-->
        <div id="page-content-wrapper">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-4 column">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>订单ID</th>
                                <th>总金额</th>
                            </tr>
                            <tbody>
                            <tr>
                                <th>${OrderDTO.orderId}</th>
                                <th>${OrderDTO.orderAmount}</th>
                            </tr>
                            </tbody>
                        </table>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>商品ID</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                <#list OrderDTO.getOrderDetailList() as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                </tr>
                </#list>
                            </tbody>
                        </table>



                        <div class="col-md-12 column">
<#if OrderDTO.getOrderStatusEnum().msg =="新订单">
    <a href="/sell/seller/order/finish?orderId=${OrderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
    <a href="/sell/seller/order/cancel?orderId=${OrderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
</#if>
                        </div>
                    </div>
                </div>
            </div>
</div>
</div>

</body>

</html>