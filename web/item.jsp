<%@ page import="bean.Item" %>
<%@ page import="service.ItemService" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/16
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%
    session.setAttribute("url","item.jsp");
%>
<%
    ItemService itemService = new ItemService();
    itemService.init();
    boolean next = true;
    String headerID = request.getParameter("id");
    if (headerID == null) {
        next = false;
    } else {
        for(char c : headerID.toCharArray()){
            if(c < '0' || c > '9') {
                next = false;
                break;
            }
        }
    }
    Item item = itemService.getItem(!next?6:Integer.parseInt(request.getParameter("id")));
    if (item == null) {
        item = itemService.getItem(6);
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=item.getName() %></title>
    <link href="templates/css/item.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="templates/js/item.js"></script>

</head>
<body>
<%@include file="all.inc.jsp"%>
    <div class="container">
        <div class="row w-100">
            <div class="col-md-5">
                <img src="<%=item.getImg()%>" class="item-img" alt="文物的图片">
                <div style="margin-bottom: 20px;margin-top: 20px"><strong>介绍视频</strong></div>
                <video src="<%=item.getVideo()%>" height="180px" controls="controls"></video>
            </div>
            <div class="col-md-5 item-right-border">
                <h2><%=item.getName()%></h2>
                <p><%=item.getDescription()%></p>
                <div class="btn-group" data-toggle="buttons">
                    <button type="button" name="options" id="option1" class="btn btn-primary item-button" onclick='like("<%=item.getId()%>")'> 喜欢
                    <% if (headerPermission == null || (Integer)headerPermission < 1) {%>
                        <button type="button"  name="options" class="btn btn-primary item-button" onclick="showMessage('请先登录')"> 收藏
                    <% } else {%>
                    <button type="button" name="options" class="btn btn-primary item-button"
                            onclick='collection("<%=item.getId()%>")'> 收藏
                    <% } %>
                </div>
                <table class="table table-striped">
                    <caption>展品属性</caption>
                    <thead>
                    </thead>
                    <tbody>
                    <tr>
                        <td>类型</td>
                        <td><%=item.getGenre()%></td>
                    </tr>
                    <tr>
                        <td>馆藏地点</td>
                        <td><%=item.getLocation()%></td>
                    </tr>
                    <tr>
                        <td>出土年份</td>
                        <td><%=item.getTime()%></td>
                    </tr>
                    <tr>
                        <td>热度</td>
                        <td id="hot"><%=item.getHot()%></td>
                    </tr>
                    <tr>
                        <td>上架时间</td>
                        <td><%=item.getTimeReleased()%></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-2 item-right-border">
                <table class="table table-hover text-center">
                    <caption class="text-center">博物推荐</caption>
                    <tbody>
                    <tr>
                        <td><a href="">鲍天成透雕浮槎犀角杯</a></td>
                    </tr>
                    <tr>
                        <td><a href="">清 虚谷 枇杷图轴</a></td>
                    </tr>
                    <tr>
                        <td><a href="">安喜宫绣佛像<span class="badge">新</span></a></td>
                    </tr>
                    <tr>
                        <td><a href="">满族平金绣云龙纹朝袍<span class="badge">新</span></a></td>
                    </tr>
                    <tr>
                        <td><a href="">清 金农 梅花图轴<span class="badge">新</span></a></td>
                    </tr>
                    </tbody>
                </table>
                <table class="table table-hover text-center">
                    <caption class="text-center">好友推荐</caption>
                    <tbody>
                    <tr>
                        <td>张三</td>
                    </tr>
                    <tr>
                        <td>李四</td>
                    </tr>
                    <tr>
                        <td> 王五</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
