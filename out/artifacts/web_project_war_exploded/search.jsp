<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/16
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%
    session.setAttribute("url", "search.jsp");
%>
<%
    String searchKey = request.getParameter("searchKey");
    searchKey = searchKey == null ? "" : searchKey;

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索</title>
    <link href="templates/css/search.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="templates/js/search.js"></script>
    <script src="templates/js/api/search.js"></script>
</head>
<body>
<%@include file="all.inc.jsp" %>
<div class="container">
    <div class="txt-head pull-right">
        <input type="text" id="key" value="<%=searchKey%>" name="key" class="input">
        <button class="btn btn-primary" id="search">搜索</button>
    </div>
    <p class="search-result"></p>
    <div class="lay-out">
        <div class="lay-head">
            <div>
                <label> 排序方式</label>&nbsp;&nbsp;
                <label class="hv-red" for="hot">热度</label>
                <input type="radio" checked name="surf-by" id="hot">&nbsp;
                <label class="hv-red" for="name">名称</label>
                <input type="radio" name="surf-by" id="name">&nbsp;
            </div>
        </div>
        <div class="lay-main">
            <%for (int i = 0; i < 3; i++) {%>
            <div class="container">
                <%for (int j = 0; j < 3; j++) {%>
                <div class="col-md-4">
                    <div class="lay-item">
                        <div class="row">
                            <div class="lay-img col-md-6">
                                <a><img alt=""></a>
                            </div>
                            <div class="col-md-6">
                                <div class="lay-title row text-center"></div>
                                <div class="lay-des row">
                                    <p class="lay-des"></p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="lay-btn text-center">
                                <button class="lay-bt btn btn-primary btn-sm">查看</button>
                                <button class="lay-bt2 btn btn-primary btn-sm">热度<span class="hot"></span></button>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
            <%}%>
        </div>
        <div class="lay-footer">
              <span class="p-num">
                <a class="pn-prev hv-red hava-bor" href="javascript:;">
                  <span>&lt;</span>
                  <em>上一页</em>
                </a>
                <a href="javascript:;" class="curr paN">1</a>
                <a href="javascript:;" class="paN">2</a>
                <a href="javascript:;" class="paN">3</a>
                <a href="javascript:;" class="paN">4</a>
                <a href="javascript:;" class="paN">5</a>
                <a href="javascript:;" class="paN">6</a>
                <a href="javascript:;" class="paN">7</a>
                <a href="javascript:;" class="paN">8</a>
                <a href="javascript:;" class="paN">9</a>
                <a href="javascript:;" class="paN">10</a>
                <a href="javascript:;" class="paN">11</a>
                <a href="javascript:;" class="paN">12</a>
                <a class="pn-last hv-red hava-bor" href="javascript:;">
                  <em>下一页</em>
                  <span>&gt;</span>
                </a>
              </span>
            <span class="p-skip">
                <em>
                  共<b>100</b>页&nbsp;&nbsp;到第
                </em>
                <input type="number" min="1" max="10" class="input-txt" name="" value="1">
                <em>页</em>&nbsp;&nbsp;
                <a href="javascript:;" class="btn btn-info">确定</a>
              </span>
        </div>
    </div>
</div>
</body>
</html>
