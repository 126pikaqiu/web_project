<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    String site = "dashboard.jsp";
    Object permission = session.getAttribute("permission");
    if(permission == null || user == null
            || (Integer)permission < 1) {
        response.sendRedirect(site);
        return;
    }
//    else if("login.jsp".equals(session.getAttribute("url"))){
//        site = "dashboard.jsp";
//        response.setStatus(response.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location", site);
//    }
%>

