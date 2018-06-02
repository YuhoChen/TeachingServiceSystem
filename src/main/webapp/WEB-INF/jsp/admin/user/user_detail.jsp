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
		<form action="${ctx }/admin/user/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">
			<div class="form-group">
                 <label class="col-sm-2 control-label">用户名</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" id="username" name="username" ${empty n.id ? '' : 'readonly' } value="${n.username }">
                 </div>
             </div>	
             <div class="form-group">
                 <label class="col-sm-2 control-label">昵称</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" id="nickName" name="nickname" ${empty n.id ? '' : 'readonly' } value="${n.nickname }">
                 </div>
             </div>
             <div class="form-group">
                 <label class="col-sm-2 control-label">邮箱</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" id="email" name="email" ${empty n.id ? '' : 'readonly' } value="${n.email }">
                 </div>
             </div>
		</form>
</div>

<%@include file="/common/foot.jsp" %>

<script>
$('#provinceId').change(function() {
	var provinceId = $(this).val();
	$('#cityId').html('');
	$('#districtId').html('');
	if (provinceId != '') {
		$.ajax({
			url: "${ctx}/admin/province/findArea?provinceId=" + provinceId,
			dataType: "json",
			success: function(array) {
				$('#cityId').append('<option value="">城市</option>');
				for (var i = 0; i < array.length; i++) {
					var obj = array[i];
					var option = '<option value="' + obj.id + '">' + obj.name + '</option>';
					$('#cityId').append(option);	
				}
			}
		});
	}
	
});

$('#cityId').change(function() {
	var cityId = $(this).val();
	$('#districtId').html('');
	if (cityId != '') {
		$.ajax({
			url: "${ctx}/admin/province/findArea?cityId=" + cityId,
			dataType: "json",
			success: function(array) {
				$('#districtId').append('<option value="">地区</option>');
				for (var i = 0; i < array.length; i++) {
					var obj = array[i];
					var option = '<option value="' + obj.id + '">' + obj.name + '</option>';
					$('#districtId').append(option);	
				}
			}
		});
	}
	load_filter_user();
});

$('#districtId').change(function() {
	load_filter_user();
});

$(document).ready(function() {
	init_images('image1');
});
</script>
</body>

