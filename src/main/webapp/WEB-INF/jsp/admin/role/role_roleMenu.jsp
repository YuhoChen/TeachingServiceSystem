<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="menu"/>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
			<div class="col-sm-4">
	                <div class="ibox float-e-margins">
						<div class="ibox-content">
							<div id="jstree1">
	                         
							</div>
						</div>     
					</div>
			</div>
        </div>
    </div>

<%@include file="/common/foot.jsp" %>
<style>
.jstree-open>.jstree-anchor>.fa-folder:before {
	content: "\f07c"
}

.jstree-default .jstree-icon.none {
	width: 0
}
</style>
	<script>
	$(document).ready(function() {
		var tree = $("#jstree1");
		var roleId = '${param.roleId}';
		tree.jstree({
			plugins: ["checkbox" ],
			'core' : {
				'data' : {
					"url" : "${ctx}/admin/role/getMenus?roleId=" + roleId,
					"dataType" : "json"
				},
				"check_callback": true
			}
		}).bind("loaded.jstree", function (e, data) {
	    	data.instance.close_all(); 
	    });
	  
	});
    
	function getSelectedIds() {
		return $("#jstree1").jstree('get_selected');	
	}
    </script>

</body>
</html>