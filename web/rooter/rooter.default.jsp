<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    String site = "/index.jsp";
    Object permission = session.getAttribute("permission");
    if(permission == null || user == null
            || (Integer)permission < 1) {
        response.sendRedirect(site);
        return;
    }
%>

