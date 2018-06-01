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
    <form action="${ctx }/admin/exam/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">


    <div class="form-group">
        <label class="col-sm-2 control-label">课程</label>
        <div class="col-sm-4">
            <select type="text" class="form-control" id="course" name="courseNumber.id" value="${n.courseNumber}">
                <c:forEach items="${courseDTOList}" var="course">
                    <option value="${course.id}" ${n.courseNumber.id eq course.id ?  'selected' : ''}>${course.courseNumber}:${course.courseName}</option>
                </c:forEach>

            </select>
        </div>
        <label class="col-sm-2 control-label">考试时间</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="examTime" name="examTime" value="<fmt:formatDate value='${n.examTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="laydate({format: 'YYYY-MM-DD hh:mm:ss'})">
        </div>

    </div>


    <div class="form-group">
        <label class="col-sm-2 control-label">考试地点</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="examPlace" name="examPlace" value="${n.examPlace}">
        </div>

        <label class="col-sm-2 control-label">考点容量</label>
        <div class="col-sm-4">
            <input type="number" class="form-control" id="examCapacity" name="examCapacity" value="${n.examCapacity}">
        </div>

    </div>


    <div class="form-group">
        <label class="col-sm-2 control-label">监考人员</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="invigilator" name="invigilator" value="${n.invigilator}">
        </div>

        <label class="col-sm-2 control-label">备注</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="remark" value="${n.remark}">
        </div>
    </div>



    </form>
</div>

<%@include file="/common/foot.jsp" %>

</body>

