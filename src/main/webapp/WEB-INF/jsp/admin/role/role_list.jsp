<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="role"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/role/list" >
						<!-- 查询开始 -->
						<div class="form-group">
							<button class="btn btn-primary form-control" type="submit"><i class="fa fa-check"></i>&nbsp;查询</button>
						</div>
						<!-- 查询结束 -->
					</form>

					<div id="operation">
						<p>

							<button type="button" class="btn btn-outline btn-primary" onclick="add()">新增</button>



							<button type="button" class="btn btn-outline btn-info" onclick="edit()">详情</button>



							<button type="button" class="btn btn-outline btn-danger" onclick="del();">删除</button>
						</p>
					</div>
					<table id="table" data-toggle="table" data-height="400" data-click-to-select="true" data-mobile-responsive="true">
						<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="id">id</th>
							<th data-field="name">角色</th>
							<th data-field="description">描述</th>
							<th data-field="operation">操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${list }" var="n" varStatus="idx">
							<tr data-index="${idx.index }">
								<td class="bs-checkbox">
									<input data-index="0" name="btSelectItem" type="checkbox">
								</td>
								<td style="">${n.id }</td>
								<td style="">${n.name }</td>
								<td style="">${n.description }</td>
								<td>
									<button type="button" class="btn btn-sm btn-outline btn-info" onclick="editRoleMenu('${n.id}')">菜单权限</button>
									<button type="button" class="btn btn-sm btn-outline btn-info" onclick="editAuthority('${n.id}')">其它权限</button>
								</td>
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
    function add() {
        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: [layer_default_width, layer_default_height], //宽高
            content: "${ctx}/admin/role/detail",
            btn: ['确定', '取消'],
            yes: function(index, layero){ //或者使用btn1
                var detailForm = layer.getChildFrame('form', index);
                console.log(detailForm);
                detailForm.ajaxSubmit({
                    success: function(data) {
                        if (isSuccess(data)) {
                            layer.close(index);
                            reload();
                        }
                        else {

                        }
                    }
                });
            }, cancel: function(index){ //或者使用btn2
                //按钮【按钮二】的回调
            }
        });
    }

    function edit() {
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
            content: "${ctx}/admin/role/detail?id=" + id,
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

    // 编辑权限
    //编辑权限
    function editAuthority(id) {
        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: [layer_default_width, layer_default_height], //宽高
            content: "${ctx}/admin/role/authorityDetail?roleId=" + id,
            btn: ['确定', '取消'],
            yes: function(index, layero){ //或者使用btn1
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var ids = iframeWin.getIds();
                console.log('ids: ' + ids);
                $.ajax({
                    url: "${ctx}/admin/role/updateAuthority",
                    type: "post",
                    data: {
                        roleId: id,
                        authorityIds: ids.join(',')
                    },
                    success: function(data) {
                        if (isSuccess(data)) {
                            layer.close(index);
                        }
                    }
                });
            }, cancel: function(index){ //或者使用btn2
                //按钮【按钮二】的回调
            }
        });
    }
    function editRoleMenu(id) {
        layer.open({
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: [layer_default_width, layer_default_height], //宽高
            content: "${ctx}/admin/role/roleMenu?roleId=" + id,
            btn: ['确定', '取消'],
            yes: function(index, layero){ //或者使用btn1
                var iframeWin = window[layero.find('iframe')[0]['name']];
                var ids = iframeWin.getSelectedIds();
                $.ajax({
                    url: "${ctx}/admin/role/updateRoleMenu",
                    type: "post",
                    data: {
                        roleId: id,
                        menuIds: ids.join(',')
                    },
                    success: function(data) {
                        if (isSuccess(data)) {
                            layer.close(index);
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
                url: "${ctx}/admin/role/delete",
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
    paging('pagination', ${page.totalPages}, ${page.page});

</script>
</body>
</html>