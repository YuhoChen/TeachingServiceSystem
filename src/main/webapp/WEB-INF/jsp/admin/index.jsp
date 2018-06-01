<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="enum" uri="http://www.yuhao.com/enum" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>yuhao</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
   	<link rel="shortcut icon" href="/favicon.ico">
    <style type="text/css">
        .banner_style{
            /*Microsoft YaHei,SimYou*/
            font-family: YouYuan;
            font-size: 40px;
            margin-left: 56px;
            margin-bottom: 30px;
            margin-top: 30px;
            color: #f34a53;
        }
    </style>
   	<%@include file="/common/jscss.jsp" %>
    
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="${user.headImage }" width="80" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="block m-t-xs"><strong class="font-bold">${user.username }</strong></span>
                                <span class="text-muted text-xs block">${enum:list('UserType', user.type)}<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="${ctx }/admin/user/editHeadImage">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="profile.html">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="${ctx }/admin/user/changePassword">修改密码</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="${ctx }/j_spring_security_logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">H+
                        </div>
                    </li>
                    
                    <!-- 菜单开始 -->
                    <%@include file="index_menu.jsp" %>
                    <!-- 菜单结束 -->
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                	<div style="text-align:left;">
                        <p style="height: 1px"></p>
                		<span class="banner_style">yuhao</span>
                        <p style="height: 1px"></p>
                	</div>
                </nav>
            </div>

            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/admin/index/index_v3">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>


                <a href="${ctx }/j_spring_security_logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>


            </div>

            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/admin/index/index_v3?v=4.0" frameborder="0" data-id="${ctx}/admin/index/index_v3" seamless></iframe>
            </div>
            <div class="footer">
            </div>
        </div>
        <!--右侧部分结束-->

    </div>
	<script>
		$(document).ready(function() {
		<c:if test="${not empty param.target}">
		var target = "${param.target}";
		if (target) {
			$('a.J_menuItem[href="${ctx}/admin' + target + '"]').click();
		}
		//$('#')
		</c:if>
			
		});
	</script>
</body>

</html>