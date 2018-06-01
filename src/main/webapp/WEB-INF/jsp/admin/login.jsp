<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>管理后台 - 登录</title>

    <link rel="shortcut icon" href="/favicon.ico"> <link href="${ctx }/res/hplus/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">

    <link href="${ctx }/res/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx }/res/hplus/css/animate.min.css" rel="stylesheet">
    <link href="${ctx }/res/hplus/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_self">
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name"></h1>

        </div>
        <h3></h3>

        <form class="m-t" role="form" id="form" action="${ctx }/login" method="post">
            <%-- <form class="m-t" role="form" id="form" action="${ctx }/j_spring_security_check" method="post"> --%>
            <div class="form-group">
                <input type="text" name="j_username" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" name="j_password" class="form-control" value="333333" placeholder="密码" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


               <!-- <p class="text-muted text-center">
                    <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
                </p>-->

        </form>
    </div>
</div>
<script src="${ctx }/res/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx }/res/hplus/js/bootstrap.min.js?v=3.3.5"></script>
</body>

</html>