<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="user"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<br>
		<br>
		<div class="col-xs-8 col-xs-offset-2">
			<form action="${ctx }/admin/user/saveChangePassword" id="reset-form" class="form-horizontal" method="post">
				<input type="hidden" name="userId" value="${n.id }"/>
				<div class="form-group">
					<label class="col-sm-5 control-label">旧密码</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" name="oldPassword" id="Password" value="" placeholder="请输入旧密码" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-5 control-label">新密码</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" name="newPassword" id="newPassword" value="" placeholder="请输入新密码" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-5 control-label">确认新密码</label>
					<div class="col-sm-6">
						<input type="password" class="form-control" name="confirmPassword" id="confirmPassword" value="" placeholder="请输入确认新密码" required>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>

<%@include file="/common/foot.jsp" %>

</body>

