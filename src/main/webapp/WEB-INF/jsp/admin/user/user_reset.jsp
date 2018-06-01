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
    		<div class="col-xs-4 col-xs-offset-4">
    			<form action="${ctx }/admin/user/resetPassword" id="reset-form" class="form-horizontal" method="post">
					<input type="hidden" name="userId" value="${n.id }"/>
					
					 <div class="form-group">
		                 <label class="col-sm-4 control-label">新密码</label>
		                 <div class="col-sm-8">
		                 	<input type="password" class="form-control" name="newPassword" id="newPassword" value="" placeholder="请输入新密码">
		                 </div>
		             </div>	
		             <div class="form-group">
		                 <label class="col-sm-4 control-label">确认新密码</label>
		                 <div class="col-sm-8">
		                 	<input type="password" class="form-control" id="confirmPassword" value="" placeholder="请输入确认新密码">
		                 </div>
		             </div>
		             <div class="form-group">
		                 <label class="col-sm-4 control-label"></label>
		                 <div class="col-sm-8">
		                 	<input type="submit" class="btn btn-primary full-width" value="提交">
		                 </div>
		             </div>
				</form>
    		</div>
    	</div>
</div>

<%@include file="/common/foot.jsp" %>

</body>
</html>
