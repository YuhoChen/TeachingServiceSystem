<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp"%>


<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<form id="form" method="post" action="${ctx }/admin/user/saveHeadImage" enctype="multipart/form-data">
				<div class="img_show img_show1">
					<img src="${user.headImage }" width="103" height="103" alt=""> 
					<input name="file" type="file" class="upfile" accept="*/*">
				</div>
				<br />
				<input type="submit" class="btn btn-primary form-control" value="保存"/>
				</form>
			</div>
		</div>
	</div>

	<%@include file="/common/foot.jsp"%>

	<script>
$(document).ready(function() {
	$('.img_show').each(function(){
		var $this = $(this);
		var btn = $this.find('.upfile');
		var img = $this.find('img');
		btn.on('change', function(){
			var file = $(this)[0].files[0];
		    var imgSrc = $(this)[0].value;
		    var url = URL.createObjectURL(file);
		    if (!/\.(jpg|jpeg|png|JPG|PNG|JPEG)$/.test(imgSrc)){
		    	alert("请上传jpg或png格式的图片！");
		     	return false;
		    } else {
		        img.attr('src',url);
		        img.css({'opacity':'1'});
		    }
		 });
	});
});

</script>
</body>
</html>
