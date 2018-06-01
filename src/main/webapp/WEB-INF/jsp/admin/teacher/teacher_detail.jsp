<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="teacher"/>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <form action="${ctx }/admin/teacher/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">



    <div class="form-group">
        <label class="col-sm-2 control-label">教职工编号</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" ${empty n.id ? '' : 'readonly' } id="teacherNumber" name="teacherNumber" value="${n.teacherNumber}" >
        </div>

        <label class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" ${empty n.id ? '' : 'readonly' } id="name" name="name" value="${n.name}">
        </div>
    </div>


    <div class="form-group">

        <label class="col-sm-2 control-label">籍贯</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="nativePlace" id="nativePlace"value="${n.nativePlace}">
        </div>

        <label class="col-sm-2 control-label">性别</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" ${empty n.id ? '' : 'readonly' } id="sex" name="sex" value="${n.sex}">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label">身份证号</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" ${empty n.id ? '' : 'readonly' } id="idCard" name="idCard" value="${n.idCard}">
        </div>

        <label class="col-sm-2 control-label">出生日期</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="birth" name="birth" value="<fmt:formatDate value='${n.birth}' pattern='yyyy-MM-dd'/>" onclick="laydate({format: 'YYYY-MM-DD'})">
        </div>

    </div>



    <div class="form-group">
        <label class="col-sm-2 control-label">手机号</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="phone" name="phone" value="${n.phone}">
        </div>
        <label class="col-sm-2 control-label">邮箱</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="email"name="email" value="${n.email}">
        </div>

    </div>


    <div class="form-group">

        <label class="col-sm-2 control-label">院/系</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" ${empty n.id ? '' : 'readonly' } id="college" name="college" value="${n.college}">
        </div>

    </div>

        <c:if test="${empty n.id}">
            <div class="form-group">
                <label class="col-sm-2 control-label">密码</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="password" name="password" >
                </div>

                <label class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="confirm" name="confirm" >
                </div>
            </div>
        </c:if>
    </form>
</div>

<%@include file="/common/foot.jsp" %>

</body>

