<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/16
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(!"unauthorized".equalsIgnoreCase(session.getAttribute("authorization").toString())) {

    }
    // 重定向到新地址
    String site = new String("/index.jsp");
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
%>

