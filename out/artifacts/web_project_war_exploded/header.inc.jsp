<%@ page import="bean.User" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/15
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="templates/css/header.inc.css" type="text/css" rel="stylesheet"/>
<nav class="navbar navbar-default" role="navigation" id="url_info" class="<%= session.getAttribute("url")%>">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="templates/img/web_img/logo.png"><img src="templates/img/web_img/logo.png" class="logo-png" alt="在线博物馆"></a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
            <li><a href="search.jsp"><span class="glyphicon glyphicon-search"></span> 搜索</a></li>
            <%     Object headerPermission = session.getAttribute("permission");
                User headerUser = (User)session.getAttribute("user");
                if(headerPermission == null || (Integer)headerPermission < 1) {%>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-log-in"></span> 未登录
                </a>
                <ul class="dropdown-menu">
                    <li><a href="login.jsp">登录</a></li>
                    <li><a href="register.jsp">注册</a></li>
                </ul>
            </li>
            <%} else {%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span><%= headerUser.getName()%>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="collection.jsp">查看收藏夹</a></li>
                        <li><a href="dashboard/index.jsp">用户中心</a></li>
                        <li class="divider"></li>
                        <li><a href="#" onclick='Logout("<%= session.getAttribute("url")%>")'>退出登录</a></li>
                    </ul>
                </li>
            <%}%>
            <% if(session.getAttribute("permission") != null && (Integer)(session.getAttribute("permission")) == 2) {%>
                <li><a href="dashboard/index.jsp"><span class="glyphicon glyphicon-dashboard"></span>控制台</a></li>
            <%}%>
        </ul>
    </div>
</nav>
<script src="templates/js/header.inc.js"></script>
<script src="templates/js/api/index.js"></script>
<script src="templates/js/api/users.js"></script>
<script src="templates/js/api/item.js"></script>
<script src="templates/js/api/collections.js"></script>
<script src="templates/js/api/search.js"></script>

