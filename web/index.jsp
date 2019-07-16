<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/15
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% session.setAttribute("authorization","unauthorized");%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>在线博物馆</title>
  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="templates/css/index.css" type="text/css" rel="stylesheet"/>
</head>
  <body>

  <%@include file="header.inc.jsp"%>
  <div id="myCarousel" class="carousel slide">
      <!-- 轮播（Carousel）指标 -->
      <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <!-- 轮播（Carousel）项目 -->
      <div class="carousel-inner">
          <div class="item active">
              <img src="templates/img/art_img/2.jpg" id=234 class="carousel-img click-img" alt="First slide">
              <div class="carousel-caption">标题 1</div>
          </div>
          <div class="item">
              <img src="templates/img/art_img/3.jpg" id=123 class="carousel-img click-img" alt="Second slide">
              <div class="carousel-caption">标题 2</div>
          </div>
          <div class="item">
              <img src="templates/img/art_img/4.jpg" id=446 class="carousel-img click-img" alt="Third slide">
              <div class="carousel-caption">标题 3</div>
          </div>
      </div>
      <!-- 轮播（Carousel）导航 -->
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
      </a>
  </div>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="templates/js/index.js"></script>
  </body>
</html>
