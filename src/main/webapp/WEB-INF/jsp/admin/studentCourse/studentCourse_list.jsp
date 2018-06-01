<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="studentCourse"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/studentCourse/list" >
                        <!-- 查询开始 -->
                        <div class="form-group">
                            <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-search"></i>&nbsp;查询</button>
                        </div>
                        <!-- 查询结束 -->
                    </form>

                    <div>
                        <p>

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
                            <th  >操作</th>
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
                                <c:if test="${n.selected==false}">
                                    <td ><a onclick="elective(${n.id})">选择</a></td>
                                </c:if>
                                <c:if test="${n.selected==true}">
                                    <td ><a onclick="retreat(${n.id})">退选</a></td>
                                </c:if>
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

    function retreat(id) {

        var msg = '确认退选该课程吗?';
        layer.confirm(msg, {
            icon: 3,
            title: '警告'
        }, function (index) {
            $.ajax({
                url: "${ctx }/admin/studentCourse/retreat",
                data: {
                    id: id
                },
                success: function (data){
                    if (isSuccess(data)) {
                        layer.close(index);
                        reload();
                    }
                }
            });
        });

    }

    function elective(id) {

        var msg = '确认选择该课程吗?';
        layer.confirm(msg, {
            icon: 3,
            title: '警告'
        }, function (index) {
            $.ajax({
                url: "${ctx }/admin/studentCourse/elective",
                data: {
                    id: id
                },
                success: function (data){
                    if (isSuccess(data)) {
                        layer.close(index);
                        reload();
                    }
                }
            });
        });

    }

    var page = "${page.page}";
    function reload() {
        page_submit(page);
    }

    paging('pagination', ${page.totalPages}, ${page.page});

</script>
</body>
