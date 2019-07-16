<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/15
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="templates/css/header.inc.css" type="text/css" rel="stylesheet"/>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="templates/img/web_img/logo.png"><img src="templates/img/web_img/logo.png" class="logo-png" alt="在线博物馆"></a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
            <li><a href="search.jsp"><span class="glyphicon glyphicon-search"></span> 搜索</a></li>
            <% if("unauthorized".equalsIgnoreCase(session.getAttribute("authorization").toString())) {%>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> 未登录</a></li>
            <%} else {%>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-user"></span><%= session.getAttribute("username")%></a></li>
            <%}%>
            <% if("admin".equalsIgnoreCase(session.getAttribute("authorization").toString()) ) {%>
                <li><a href="dashboard.jsp"><span class="glyphicon glyphicon-dashboard"></span>控制台</a></li>
            <%}%>
        </ul>
    </div>
</nav>

