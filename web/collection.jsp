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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="rooter/rooter.default.jsp"%>
<%
    CollectionService collectionService = new CollectionService();
    collectionService.init();
    User user = (User)session.getAttribute("user");
    ArrayList<Item> items = collectionService.getItems(user.getUserID());
%>
<html>
<head>
    <title>收藏夹</title>
</head>
<body>
<%@include file="header.inc.jsp"%>
<div class="container">
    <% if (items.size() == 0) {%>
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-8">你的收藏夹空空如也，去找找心仪的文物 <a href="index.jsp">回到首页</a></div>
        </div>
    <% } else {%>
    <div class="row">
        <div class="col-col-md-3">图片</div>
        <div class="col-md-2">博物名称</div>
        <div class="col-md-2">馆藏地点</div>
        <div class="col-md-2">出土（或完成）时间</div>
        <div class="col-md-2">热度</div>
        <div class="col-md-1">操作</div>
    </div>
    <%for(Item item: items) { %>
        <div class="row">
            <div class="col-col-md-3"><img src="<%=item.getImg()%>" alt="博物图片"></div>
            <div class="col-md-2"><%=item.getName()%></div>
            <div class="col-md-2"><%=item.getLocation()%></div>
            <div class="col-md-2"><%=item.getTime()%></div>
            <div class="col-md-2"><%=item.getHot()%></div>
            <div class="col-md-1">
                <label class="btn btn-primary item-button">
                    <input type="radio" name="options" class="item" id="<%=item.getId()%>" > 详情
                </label>
                <label class="btn btn-primary item-button">
                    <input type="radio" name="options" class="delete"> 删除
                </label>
            </div>
        </div>
    <% }%>
    <% }%>
</div>
</body>
</html>
