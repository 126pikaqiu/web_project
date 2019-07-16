<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = (String)session.getAttribute("username");
    String pwd = (String)session.getAttribute("pwd");
    String site = "index.jsp";
    Object permission = session.getAttribute("permission");
    if(permission == null || name == null
            || pwd == null
            || (Integer)permission < 1) {
        // 重定向到新地址
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    } else if("login.jsp".equals(session.getAttribute("url"))){
        site = "index.jsp";
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }
%>

