<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="exam"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/exam/list" >
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
                            <button type="button" class="btn btn-sm btn-outline btn-danger" onclick="del()">删除</button>
                            <button type="button" class="btn btn-sm btn-outline btn-info " onclick="reOpen()"><i class="fa fa-refresh"></i> 刷新</button>
                        </p>
                    </div>
                    <table id="table" data-toggle="table" data-striped="true" data-click-to-select="true" data-mobile-responsive="true">
                        <thead>
                        <tr>
                            <th data-field="_state" data-checkbox="true"></th>
                            <th data-visible="false" data-field="id" >ID</th>
                             <th data-field="course_number" >课程</th>
                             <th data-field="exam_time" >考试时间</th>
                             <th data-field="exam_place" >考试地点</th>
                             <th data-field="exam_capacity" >考点容量</th>
                             <th data-field="invigilator" >监考人员</th>
                             <th data-field="remark" >备注</th>


                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list }" var="n" varStatus="idx">
                            <tr data-index="${idx.index }" data-id="${n.id }">
                                <td class="bs-checkbox"><input data-index="0" name="btSelectItem" type="checkbox"></td>
                                <td data-visible="false">${n.id }</td>
                                 <td >${n.courseNumber.courseNumber}:${n.courseNumber.courseName}</td>
                                 <td > <fmt:formatDate value="${n.examTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                 <td >${n.examPlace}</td>
                                 <td >${n.examCapacity}</td>
                                 <td >${n.invigilator}</td>
                                 <td >${n.remark}</td>
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
            content: "${ctx }/admin/exam/detail",
            btn: ['确定', '取消'],
            yes: function(index, layero){ //或者使用btn1
                var detailForm = layer.getChildFrame('form', index);
                console.log(detailForm);
                var course= $('#course', detailForm).val();
                if(!!!course){error('请选择课程.');return false;}
                var examTime = $('#examTime', detailForm).val();
                if(!!!examTime){error('请输入考试时间.');return false;}

                var examPlace = $('#examPlace', detailForm).val();
                if(!!!examPlace){error('请输入考试地点.');return false;}

                var examCapacity = $('#examCapacity', detailForm).val();
                if(!!!examCapacity){error('请输入考点容量.');return false;}

                var invigilator = $('#invigilator', detailForm).val();
                if(!!!invigilator){error('请输入监考人员.');return false;}


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
        k.layer.edit("${ctx }/admin/exam/detail?id=" + id);
    }
    var page = "${page.page}";
    function reload() {
        page_submit(page);
    }

    function del() {
        var selections = $('#table').bootstrapTable('getAllSelections');
        if (selections.length < 1) {
            error(k.msg.requireAtLeastOne);
            return;
        }
        var ids = new Array();
        for (var i = 0; i < selections.length; i++) {
            ids.push(selections[i].id);
        }
        k.layer.del("${ctx }/admin/exam/delete", config={msg:'您确定要删除选中的安排吗？', ids: ids});
    }
    paging('pagination', ${page.totalPages}, ${page.page});

</script>
</body>
