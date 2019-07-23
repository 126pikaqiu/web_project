<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Item" %>
<%@ page import="service.ItemService" %>
<%@ page import="bean.User" %>
<%@ page import="bean.Collection" %>
<%@ page import="service.CollectionService" %>
<%--
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
    ItemService itemService = new ItemService();
    itemService.init();
    User collectionUser = (User)session.getAttribute("user");
    ArrayList<Item> items = itemService.getItems(collectionUser.getUserID());
    CollectionService collectionService = new CollectionService();
    collectionService.init();
%>
<html>
<head>
    <title>收藏夹</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="templates/css/collection.css" type="text/css" rel="stylesheet"/>
    <script src="templates/js/collection.js"></script>
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
                <a href="item.jsp?id=<%=item.getId()%>"><%=item.getName()%></a>
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
                <% if(collectionService.getPermission(new Collection(collectionUser.getUserID(),item.getId()))){%>
                <button class="btn btn-primary btn-sm" type="button"  onclick="UpdateCollection(<%=item.getId()%>)"> 不公开</button>
                <%} else {%>
                <button class="btn btn-primary btn-sm" type="button"  onclick="UpdateCollection(<%=item.getId()%>)"> 公开</button>
                <%}%>
                <button class="btn btn-primary btn-sm delete" type="button"
                        onclick='DeleteCollection("<%=item.getId()%>")'> 删除 </button>
            </div>
        </div>
    </div>
    <% }%>
    <% }%>
</div>
</body>
</html>
