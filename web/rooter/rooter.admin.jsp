<%@ page import="service.AccountService" %>
<%@ page import="service.User" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/16
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="rooter.default.jsp"%>
<%
    if ((Integer)permission == 2) {
        AccountService accountService = new AccountService();
        accountService.init();
        String name = ((User) user).getName();
        String pwd = ((User) user).getPwd();
        if(accountService.login(name,pwd)) {
            accountService.destroy();
            User adminUser = accountService.getUser(name);
            session.setAttribute("user",adminUser);
            session.setAttribute("permission",adminUser.getPermission());
        } else {
            accountService.destroy();
            session.setAttribute("user",null);
            session.setAttribute("permission",0);
            // 重定向到新地址
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        }
    }
%>
