<%@ page import="service.ItemService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Item" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/15
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("url","index.jsp");
    ItemService index_item_service = new ItemService();
    index_item_service.init();
    ArrayList<Item> latest = index_item_service.getLatest();
    ArrayList<Item> hottest = index_item_service.getHottest();
%>
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

  <%@include file="all.inc.jsp"%>
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
              <img src="<%=hottest.get(0).getImg()%>" alt="<%=hottest.get(0).getId()%>" class="carousel-img click-img">
              <div class="carousel-caption">
                  <h4> <%=hottest.get(0).getName()%></h4>
                  <p><%=hottest.get(0).getDescription()%></p>
              </div>
          </div>
          <div class="item">
              <img src="<%=hottest.get(1).getImg()%>" alt="<%=hottest.get(0).getId()%>" class="carousel-img click-img">
              <div class="carousel-caption">
                  <h4> <%=hottest.get(1).getName()%></h4>
                  <p><%=hottest.get(1).getDescription()%></p>
              </div>
          </div>
          <div class="item">
              <img src="<%=hottest.get(2).getImg()%>" alt="<%=hottest.get(2).getId()%>" class="carousel-img click-img">
              <div class="carousel-caption">
                  <h4> <%=hottest.get(2).getName()%></h4>
                  <p><%=hottest.get(2).getDescription()%></p>
              </div>
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
  <div class="container index-margin"></div>
  <div class="container">
      <div class="row">
          <% for(int index = 0; index < 3; index++) {%>
          <div class="col-md-4">
              <div class="index-head">
                  <img src="<%=latest.get(index).getImg()%>" alt="<%=latest.get(index).getId()%>" class="index-reflect-img">
              </div>
              <div class="index-body">
                  <h4><%=latest.get(index).getName()%></h4>
                  <p><%=latest.get(index).getDescription()%></p>
              </div>
          </div>
          <%}%>
      </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  <script src="templates/js/index.js"></script>
  </body>
</html>
