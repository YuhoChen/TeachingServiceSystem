<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="notice"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <form action="${ctx }/admin/notice/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">




    <div class="form-group">
        <label class="col-sm-2 control-label">标题</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="title" name="title" value="${n.title}">
        </div>

        <label class="col-sm-2 control-label">通知发起者</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="initiator" name="initiator" value="${n.initiator}">
        </div>
    </div>


        <div class="form-group">
            <label class="col-sm-2 control-label">通知时间</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="noticeTime" name="noticeTime" value="<fmt:formatDate value='${n.noticeTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="laydate({format: 'YYYY-MM-DD hh:mm:ss'})">
            </div>
        </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">通知内容</label>
        <div class="col-sm-8">
            <%--<input type="text" class="form-control" name="content" value="${n.content}">--%>
            <textarea id="content" name="content" class="form-control" aria-required="true" aria-multiline="true" rows="8" >${n.content}</textarea>

        </div>

    </div>




    </form>
</div>

<%@include file="/common/foot.jsp" %>

</body>

