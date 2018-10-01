<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--侧边栏-->
    <#include "../common/nav.ftl">

<#--内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                        <label>商品名称</label>
                        <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!""}" />
                    </div>
                        <div class="form-group">
                            <label>商品价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!""}" />
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="text" class="form-control" value="${(productInfo.productStock)!""}" />
                        </div>
                        <div class="form-group">
                            <label>商品描述</label>
                            <input name="productDsp" type="text" class="form-control" value="${(productInfo.productDsp)!""}" />
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(productInfo.productIcon)!""} ">
                            <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!""}" />
                        </div>
                        <div class="form-group">
                            <label>商品类目</label>
                            <select name="categoryType" class="form-control">
                            <#list productCategoryList as productCategory>
                                <option value="${productCategory.categoryType}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType==productCategory.categoryType>
                                        selected</#if>
                                    >${productCategory.categoryName}
                                </option>
                            </#list>
                            </select>
                        </div>
                        <input hidden name="productId" type="text" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>

