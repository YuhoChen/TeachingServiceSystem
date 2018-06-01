<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<style>
	.title2 {
		font-size: 15px;
		font-weight: bold;
		border-bottom: 1px solid #ccc;
		margin-bottom: 10px;
		margin-top: 20px;
		width: 50px;
	}
</style>

<c:set var="module" value="menu"/>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
			<div class="col-sm-12">
	                <div class="ibox float-e-margins">
						<div class="ibox-content">
							<div id="jstree1">
	                        	<button type="button" class="btn btn-sm btn-outline btn-primary" onclick="add()" style="margin-bottom: 10px;"><i class="fa fa-plus"></i> 新增</button>
	                        	
	                        	<c:forEach items="${moduleAuthorities }" var="ma">
	                        	
	                        	<div class="panel panel-default">	
	                        		<div class="panel-heading">
	                                        <a data-toggle="collapse" data-parent="#accordion" href="tabs_panels.html#${ma.module }">
	                                    <h5 class="panel-title">
	                                            ${ma.module }
	                                    </h5>
	                                        </a>
	                                </div>
	                                <div id="${ma.module }" class="panel-collapse collapse in">
	                                    <div class="panel-body">
	                                    	<div class="row">
	                                    		<c:forEach items="${ma.authorities }" var="a">
	                                    			<div class="col-sm-3" style="margin-top: 5px;">
					                        			<label class="checkbox-inline i-checks">
						                                    <div class="icheckbox_square-green" style="position: relative;">
						                                    <input type="checkbox" ${a.contains ? 'checked="checked"' : '' } value="${a.id }" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div>
						                                    ${a.name }
						                                <a href="" onclick="edit('${a.id}')"><i class="fa fa-edit"> </i></a>
						                                </label>
	                                    			</div>
					                            </c:forEach>
	                                    		
	                                    	</div>
	                                    </div>
	                                </div>
	                            </div>    
	                            </c:forEach>
							</div>
						</div>     
					</div>
			</div>
        </div>
    </div>

<%@include file="/common/foot.jsp" %>
<script>
	function add() {
		parent.layer.open({
			title: k.btn.add,
		    type: 2,
		    skin: 'layui-layer-rim', //加上边框
		    area: [layer_default_width, layer_default_height], //宽高
		    offset: ['10px', '10px'],
		    content: '${ctx}/admin/authority/detail',
		    btn: [k.btn.confirm, k.btn.cancel],
		    yes: function(index, layero){ //或者使用btn1
		    	var detailForm = parent.layer.getChildFrame('form', index);
		    	detailForm.ajaxSubmit({
		    		success: function(data) {
			    		if (isSuccess(data)) {
				    		parent.layer.close(index);
				    		location = '${ctx}/admin/role/authorityDetail?roleId=${roleId}';
				    	} else {
				    	}
		    		}
		    	});
		    }, cancel: function(index){ //或者使用btn2
		        //按钮【按钮二】的回调
		    },
		    end: function() {
		    	// alert('end')
		    }
		}); 
	}
	
	function edit(id) {
		parent.layer.open({
			title: k.btn.edit,
		    type: 2,
		    skin: 'layui-layer-rim', //加上边框
		    area: [layer_default_width, layer_default_height], //宽高
		    offset: ['10px', '10px'],
		    content: '${ctx}/admin/authority/detail?id=' + id,
		    btn: [k.btn.confirm, k.btn.cancel],
		    yes: function(index, layero){ //或者使用btn1
		    	var detailForm = parent.layer.getChildFrame('form', index);
		    	detailForm.ajaxSubmit({
		    		success: function(data) {
			    		if (isSuccess(data)) {
				    		parent.layer.close(index);
				    		location = '${ctx}/admin/role/authorityDetail?roleId=${roleId}';
				    	} else {
				    	}
		    		}
		    	});
		    }, cancel: function(index){ //或者使用btn2
		        //按钮【按钮二】的回调
		    },
		    end: function() {
		    	// alert('end')
		    }
		}); 
	}
	
	function getIds() {
		var ids = new Array();
		$('input[type=checkbox]:checked').each(function(i) {
			ids.push($(this).val());
		});
		return ids;
	}
</script>

</body>
</html>