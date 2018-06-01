<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="course"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/course/list" >
                        <!-- 查询开始 -->
                        <div class="form-group">
                            <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-search"></i>&nbsp;查询</button>
                        </div>
                        <!-- 查询结束 -->
                    </form>

                    <div>
                        <p>
                            <button type="button" class="btn btn-sm btn-outline btn-primary" onclick="add()"><i class="fa fa-plus"></i> 新增</button>
                            <button type="button" class="btn btn-sm btn-outline btn-info " onclick="edit()"><i class="fa fa-paste"></i> 详情</button>
                            <%--<button type="button" class="btn btn-sm btn-outline btn-danger" onclick="del();">删除</button>--%>
                            <button type="button" class="btn btn-sm btn-outline btn-info " onclick="reOpen()"><i class="fa fa-refresh"></i> 刷新</button>
                        </p>
                    </div>
                    <table id="table" data-toggle="table" data-striped="true" data-click-to-select="true" data-mobile-responsive="true">
                        <thead>
                        <tr>
                             <th data-field="_state" data-checkbox="true"></th>
                             <th data-visible="false" data-field="id" >ID</th>
                             <th data-field="course_number" >课程号</th>
                             <th data-field="course_name" >课程名称</th>
                             <th data-field="teacher" >任课老师</th>
                             <th data-field="course_info" >课程信息</th>
                             <th data-field="course_credit" >学分</th>


                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list }" var="n" varStatus="idx">
                            <tr data-index="${idx.index }" data-id="${n.id }">
                                <td class="bs-checkbox"><input data-index="0" name="btSelectItem" type="checkbox"></td>
                                 <td data-visible="false">${n.id }</td>
                                 <td >${n.courseNumber}</td>
                                 <td >${n.courseName}</td>
                                 <td >${n.teacher}</td>
                                 <td >${n.courseInfo}</td>
                                 <td >${n.courseCredit}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <%@include file="/common/page.jsp" %>

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
            content: "${ctx }/admin/course/detail",
            btn: ['确定', '取消'],
            yes: function(index, layero){ //或者使用btn1
                var detailForm = layer.getChildFrame('form', index);
                console.log(detailForm);
                var courseNumber = $('#courseNumber', detailForm).val();
                if(!!!courseNumber){error('请输入课程号.');return false;}
                var courseName = $('#courseName', detailForm).val();
                if(!!!courseName){error('请输入课程名称.');return false;}

                var teacher = $('#teacher', detailForm).val();
                if(!!!teacher){error('请输入任课老师.');return false;}

                var courseCredit = $('#courseCredit', detailForm).val();
                if(!!!courseCredit){error('请输入学分.');return false;}

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

    function edit() {
        var selections = $('#table').bootstrapTable('getAllSelections');
        if (selections.length != 1) {
            error(k.msg.requireOne);
            return;
        }
        var id = selections[0].id;
        k.layer.edit("${ctx }/admin/course/detail?id=" + id);
    }
    var page = "${page.page}";
    function reload() {
        page_submit(page);
    }
    function del() {
        k.layer.del("${ctx }/admin/course/delete");
    }
    paging('pagination', ${page.totalPages}, ${page.page});

</script>
</body>
