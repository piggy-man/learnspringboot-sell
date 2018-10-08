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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!""}" />
                        </div>
                        <div class="form-group">
                            <label>TYPE</label>
                            <input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType)!""}" />
                        </div>
                        <input hidden name="categoryId" type="text" value="${(productCategory.categoryId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>