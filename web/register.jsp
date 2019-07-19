<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/7/19
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<% String fromUrl = session.getAttribute("url").toString(); %>
<% session.setAttribute("url","register.jsp"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="templates/js/register.js"></script>
    <link href="templates/css/register.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%@include file="all.inc.jsp"%>
<div class="container-fluid register-body" id="<%=fromUrl%>">
    <div class="row">
        <div class="col-md-7"></div>
        <div class="col-md-3">
            <form class="form-horizontal login-form" role="form">
                <div class="form-group login-form-group">
                    <label for="inputUsername" class="col-sm-3 control-label">username</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="inputUsername" name="username" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group login-tip login-form-group">
                    <span class="col-sm-6 info" id="name_info">用户名不能为空</span>
                </div>
                <div class="form-group login-form-group">
                    <label for="inputUsername" class="col-sm-3 control-label">email</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="inputEmail" name="email" placeholder="请输入邮箱">
                    </div>
                </div>
                <div class="form-group login-tip login-form-group">
                    <span class="col-sm-6 info" id="email_info">邮箱地址不能为空</span>
                </div>
                <div class="form-group login-form-group">
                    <label for="inputPassword" class="col-sm-3 control-label">password</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="inputPassword" name="pwd1" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group login-tip login-form-group">
                    <span class="col-sm-6 info" id="pwd_info">密码不能为空</span>
                </div>
                <div class="form-group login-form-group">
                    <label for="inputPassword" class="col-sm-3 control-label">password again</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="inputPasswordAgain" name="pwd2" placeholder="请再次输入密码">
                    </div>
                </div>
                <div class="form-group login-tip login-form-group">
                    <span class="col-sm-6 info" id="pwd_again_info">确认密码不能为空</span>
                </div>
                <div class="form-group login-form-group">
                    <label for="inputPassword" class="col-sm-3 control-label">captcha</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="inputCaptcha" name="pwd" placeholder="请再次输入验证码">
                    </div>
                </div>
                <div class="form-group login-tip login-form-group">
                    <span class="col-sm-6 info" id="captcha_info">验证码</span>
                </div>
                <div class="form-group login-button login-form-group">
                    <div class="col-sm-12">
                        <button type="button" id="login-btn" class="btn btn-primary btn-block">登录</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>