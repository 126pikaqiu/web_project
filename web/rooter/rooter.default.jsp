<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    String site = "index.jsp";
    Object permission = session.getAttribute("permission");
    if(permission == null || user == null
            || (Integer)permission < 1) {
        // 重定向到新地址
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    } else if("login.jsp".equals(session.getAttribute("url"))){
        site = "index.jsp";
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }
    System.out.println("rooter.default.jsp");
%>

