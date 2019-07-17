<%@ page import="java.util.ArrayList" %>
<%@ page import="service.Item" %>
<%@ page import="service.CollectionService" %>
<%@ page import="service.User" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/16
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<% session.setAttribute("url","collection.jsp"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="rooter/rooter.default.jsp"%>
<%
    CollectionService collectionService = new CollectionService();
    collectionService.init();
    User collectionUser = (User)session.getAttribute("user");
    ArrayList<Item> items = collectionService.getItems(collectionUser.getUserID());
%>
<html>
<head>
    <title>收藏夹</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="templates/css/collection.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="all.inc.jsp"%>
<div class="container">
    <% if (items.size() == 0) {%>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-8">你的收藏夹空空如也，去找找心仪的文物 <a href="index.jsp">回到首页</a></div>
        </div>
    <% } else {%>
    <div class="row collection-header">
        <div class="col-md-2  text-center">图片</div>
        <div class="col-md-2">博物名称</div>
        <div class="col-md-2">馆藏地点</div>
        <div class="col-md-2">出土（或完成）时间</div>
        <div class="col-md-2">热度</div>
        <div class="col-md-2">操作</div>
    </div>
    <%for(Item item: items) { %>
    <div class="row collection-item">
        <div class="col-md-2">
            <div class="item-img-body">
                <img src="<%=item.getImg()%>"  class="collection-img" alt="博物图片">
            </div>
        </div>
        <div class="col-md-2">
            <div class="collection-item-body">
                <%=item.getName()%>
            </div>
        </div>
        <div class="col-md-2">
            <div class="collection-item-body">
                <%=item.getLocation()%>
            </div>
        </div>
        <div class="col-md-2">
            <div class="collection-item-body">
                <%=item.getTime()%>
            </div>
        </div>
        <div class="col-md-2">
            <div class="collection-item-body">
                <%=item.getHot()%>
            </div>
        </div>
        <div class="col-md-2">
            <div class="collection-item-body">
                <label class="btn btn-primary btn-sm">
                    <input type="button" name="options" class="item" id="<%=item.getId()%>" > 详情
                </label>
                <label class="btn btn-primary btn-sm">
                    <input type="button" name="options" class="delete"> 删除
                </label>
            </div>
        </div>
    </div>
    <% }%>
    <% }%>
</div>
<script src="templates/js/collection.js"></script>
</body>
</html>
