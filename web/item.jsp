<%@ page import="service.Item" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/16
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%
    Item item = new Item(request.getParameter("id"),"一件文物",345,"2019-07-11");
    item.setImg("templates/img/art_img/2.jpg");
    item.setDescription("簋是一种古代食器，用来盛装煮熟的稻、粱等食物，犹如现在的饭盆，饮宴时使用。它又是一种重要的礼器，" +
            "盛行于商、周时期，使用者一般为王侯贵族。是由当时称作“金”的青铜铸成。簋与老百姓无缘，那时穷苦百姓使用的大多是陶器。" +
            "簋和鼎配套使用，盛装上食物、牺牲等供奉在神坛上祭祀天地祖先。" +
            "周代礼制中对鼎用簋的使用有严格规定：天子九鼎八簋，诸侯七鼎六簋，大夫五鼎四簋………“九鼎八簋”是最高礼仪，只有周天子才能享用，");
    item.setVideo("templates/videos/123.mp4");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=item.getName() %></title>
    <link href="templates/css/item.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="header.inc.jsp"%>
    <div class="container">
        <div class="row w-100">
            <div class="col-md-5">
                <img src="<%=item.getImg()%>" class="item-img" alt="文物的图片">
            </div>
            <div class="col-md-5 item-right-border">
                <h2><%=item.getName()%></h2>
                <p><%=item.getDescription()%></p>
                <div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-primary item-button">
                        <input type="radio" name="options" id="option1"> 喜欢
                    </label>
                    <label class="btn btn-primary item-button">
                        <input type="radio" name="options" id="option2"> 收藏
                    </label>
                </div>
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
