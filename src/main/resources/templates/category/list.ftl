<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--侧边栏-->
<#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-condensed">
                        <thead>
                        <tr>
                            <th>类目Id</th>
                            <th>名称</th>
                            <th>Type</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <#list categoryList as category>
                    <tr>
                        <td>
                            ${category.categoryId}
                        </td>
                        <td>
                            ${category.categoryName}
                        </td>
                        <td>
                            ${category.categoryType}
                        </td>
                        <td>
                            ${category.createTime}
                        </td>
                        <td>
                            ${category.updateTime}
                        </td>
                        <td>
                        <a href="/sell/seller/category/modify?categroyId=${category.categoryId}">修改</a>
                        </td>
                    </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>