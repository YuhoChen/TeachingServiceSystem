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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>登录</title>
    <link href="${ctx }/res/hplus/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx }/res/hplus/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/res/hplus/css/animate.min.css" rel="stylesheet">
    <link href="${ctx }/res/hplus/css/style.css" rel="stylesheet">
    <link href="${ctx }/res/hplus/css/login.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>
<%@include file="/common/head.jsp" %>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h2>欢迎使用 <strong>湖南大学教务系统</strong></h2>
                    </div>
                    <div class="m-b"></div>
                    <h3> <strong>通知:</strong></h3>
                    <ul class="m-b">
                        <c:forEach items="${list}" var="notice">
                            <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
                                <a onclick="detail(${notice.id})">${notice.title}   通知时间:<fmt:formatDate value="${notice.noticeTime }" pattern="yyyy-MM-dd HH:mm:ss"/></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-sm-5">
                <form action="${ctx }/login" method="post">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录湖南大学教务系统</p>
                    <input type="text" class="form-control uname" name="j_username" placeholder="账号" required />
                    <input type="password" name="j_password" class="form-control pword m-b" placeholder="密码" required/>
                    <a href="#">忘记密码了？</a>
                    <button class="btn btn-success btn-block" type="submit">登录</button>
                </form>
                <div class="form-group msg-error font_red" style="color: white;">
                    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
                        <c:if test="${'Bad credentials' == SPRING_SECURITY_LAST_EXCEPTION.message }">
                            用户名或密码不正确
                        </c:if>
                        <c:if test="${'Bad credentials' != SPRING_SECURITY_LAST_EXCEPTION.message }">
                            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'batchrunning' }">
                                <script>
                                    layer.open({
                                        type: 1,
                                        content: $('#batchrunning')
                                    });
                                </script>
                            </c:if>
                            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message != 'batchrunning' }">
                                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 计科五班
            </div>
        </div>
    </div>

    <script>
        function detail(id) {

            layer.open({
                title: '通知',
                type: 2,
                skin: 'layui-layer-rim', //加上边框
                area: [layer_default_width, layer_default_height], //宽高
                content: "${ctx}/notice?id="+id,
                btn: [ '关闭'],
                yes: function (index, layero) { //或者使用btn1
                  layer.close(index);
                }, cancel: function (index) { //或者使用btn2
                    //按钮【按钮二】的回调
                }
            });
        }

    </script>

</body>



</html>
