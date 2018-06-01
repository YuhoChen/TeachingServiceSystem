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
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/user/mySubAccounts" >
                    		<!-- 查询开始 -->
                            <%--   <input type="hidden" name="type" value="${type }" />
                              <input type="hidden" name="fromType" value="${fromType }" />
                              <div class="form-group">
                                <label for="query-user" class="sr-only">用户</label>
				                 	<input type="text" placeholder="用户名、昵称或手机" name="searchTerm" value="${query.searchTerm }" class="form-control"/> 
                            </div> --%>
                            <div class="form-group">
                            	<button class="btn btn-primary form-control" type="submit"><i class="fa fa-check"></i>&nbsp;查询</button>
                            </div>
                            <!-- 查询结束 -->
                        </form>
                    
						<div>
							<p>
								<%-- 
		                        <button type="button" class="btn btn-outline btn-primary" onclick="add()">新增</button>
		                        
		                        
		                        
		                        <button type="button" class="btn btn-outline btn-info" onclick="edit()">详情</button>
		                         --%>
		                        <button type="button" class="btn btn-outline btn-primary" onclick="audit()">审核</button>
		                    </p>
						</div>
						<table id="table" data-toggle="table" data-height="400" data-click-to-select="true" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="id">id</th>
                                            <th data-field="username">子账号名称</th>
                                            <th data-field="contactName">联系人</th>
                                            <th data-field="mobile">联系电话</th>
                                            <th data-field="address">联系地址</th>
                                            <th data-field="status">支付状态</th>
                                            <th data-field="auditStatus">审核状态</th>
                                            <!-- <th data-field="status">状态</th> -->
                                        </tr>
                                    </thead>
							<tbody>
								<c:forEach items="${list }" var="n" varStatus="idx">
								<tr data-index="${idx.index }">
									<td class="bs-checkbox">
										<input data-index="0" name="btSelectItem" type="checkbox">
									</td>
									<c:forEach items="${n.user.designers }" var="m" >
									<td style="">${m.id }</td>
									</c:forEach>
									<td style="">${n.user.username }</td>
									<td style="">${n.user.contactName }</td>
									<td style="">${n.user.mobile }</td>
									<td style="">${n.user.address }</td>
									<td style="">${enum:list('PayStatus', n.status)}</td>
									<c:forEach items="${n.user.designers }" var="m" >
									<td style="">${enum:list('AuditStatus', m.auditStatus)}</td>
									</c:forEach>
								</tr>
								</c:forEach>
							</tbody>
						</table>
                   
						<div class="row">
							<div class="col-sm-6">
								<div class="dataTables_info" id="DataTables_Table_0_info"
									role="alert" aria-live="polite" aria-relevant="all">显示第 ${(page.page - 1) * page.pageSize  + 1}
									到第 ${(page.page) * page.pageSize} 项，共 ${page.totalRecords } 项</div>
							</div>
							<div class="col-sm-6">
								<div class="dataTables_paginate paging_simple_numbers"
									id="DataTables_Table_0_paginate">
									<ul class="pagination" id="pagination">
									</ul>
								</div>
							</div>
						</div>

					</div>
                </div>
            </div>
        </div>
    </div>

<%@include file="/common/foot.jsp" %>
<script>


function audit() {
	var selections = $('#table').bootstrapTable('getAllSelections');
	if (selections.length == 0) {
		error('请选择一条记录');
		return;
	} else if (selections.length > 1) {
		error('只能选择一条记录');
		return;
	}
	var id = selections[0].id;
	
	layer.open({
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: "${ctx}/admin/user/user_audit_detail?id=" + id,
	    btn: ['确定', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    	var detailForm = layer.getChildFrame('form', index);
	    	console.log(detailForm);
	    	detailForm.ajaxSubmit({
	    		success: function(data) {
	    			if (isSuccess(data)) {
	    				layer.close(index);
	    				reload();
	    			} else {
	    			}
	    		}
	    	});
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    }
	});
}
var page = "${page.page}";
function reload() {
	page_submit(page);
}
function del() {
	var selections = $('#table').bootstrapTable('getAllSelections');
	if (selections.length < 1) {
		error('请选择要删除的记录');
		return;
	}
	layer.confirm('确认要删除吗?', {icon: 3, title:'提示'}, function(index){
		var ids = new Array();
		for (var i = 0; i < selections.length; i++) {
			ids.push(selections[i].id);
		}
		$.ajax({
			url: "${ctx}/admin/user/delete",
			data: {
				ids: ids.join(',')
			},
			success: function(data) {
				if (isSuccess(data)) {
					layer.close(index);
					reload();
				}
			}
		});
	});
	
}
function auth(){
	var selections = $('#table').bootstrapTable('getAllSelections');
	if (selections.length == 0) {
		error('请选择一条记录');
		return;
	} else if (selections.length > 1) {
		error('只能选择一条记录');
		return;
	}
	var id = selections[0].id;
	layer.open({
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: "${ctx}/admin/user/auth?userId=" + id,
	    success: function(layero, index){
	    	var roleTable = layer.getChildFrame("#roleTable", index);
	    	var els = roleTable.find('input[name="btSelectItem"]:checked');
	    	var selectedIndex = [];
	    	els.each(function(){
	    		var index = $(this).parent().parent().index();
	    		selectedIndex.push(index);
	    	});
	    	roleTable.bootstrapTable();
	    	for(var i=0; i<selectedIndex.length; i++) {
	    		roleTable.bootstrapTable('check', selectedIndex[i]);
	    	}
	    },
	    btn: ['确定', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    	var table = layer.getChildFrame("#roleTable", index);
	    	var sels = table.bootstrapTable('getSelections');
	        if (sels.length < 1) {
	    		error('请选择要赋权限的记录');
	    		return;
	    	}
	    	var ids = new Array();
			for (var i = 0; i < sels.length; i++) {
				ids.push(sels[i].id);
			}
			$.ajax({
				url: "${ctx}/admin/user/authSave",
				data: {
					ids: ids.join(','),
					userId : id
				},
				success: function(data) {
					if (isSuccess(data)) {
						layer.close(index);
						reload();
					}
				}
			});
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    } 
	});
}
paging('pagination', ${page.totalPages}, ${page.page});

function resetPwd() {
	var selections = $('#table').bootstrapTable('getAllSelections');
	if (selections.length == 0) {
		error('请选择一条记录');
		return;
	} else if (selections.length > 1) {
		error('只能选择一条记录');
		return;
	}
	var id = selections[0].id;
	
	layer.open({
	    type: 2,
	    skin: 'layui-layer-rim', //加上边框
	    area: [layer_default_width, layer_default_height], //宽高
	    content: "${ctx}/admin/user/reset?userId=" + id,
	    btn: ['确定', '取消'],
	    yes: function(index, layero){ //或者使用btn1
	    	var resetForm = layer.getChildFrame('#reset-form', index);
	    	console.log(resetForm);
	    	var newPassword = $('#newPassword', resetForm).val();
	    	var confirmPassword = $('#confirmPassword', resetForm).val();
	    	if(!!!newPassword){alert('请输入新密码.');return false;}
	    	if(!!!confirmPassword){alert('请输入确认新密码.');return false;}
	    	if(newPassword != confirmPassword){alert('两次输入密码不匹配.');return false;}
	    	resetForm.ajaxSubmit({
	    		success: function(data) {
	    			if (isSuccess(data)) {
	    				layer.close(index);
	    				reload();
	    			} else {
	    				
	    			}
	    		}
	    	});
	    }, cancel: function(index){ //或者使用btn2
	        //按钮【按钮二】的回调
	    }
	});
}
</script>
</body>
</html>