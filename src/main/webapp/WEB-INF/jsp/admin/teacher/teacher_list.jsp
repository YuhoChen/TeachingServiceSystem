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
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/teacher/list" >
                        <!-- 查询开始 -->

                        <div class="form-group">
                            <label   for="teacherNumber">教职工编号</label>
                            <input type="text"  id="teacherNumber" name="teacherNumber" value="${query.teacherNumber }" class="form-control"/>
                            <label   for="name">姓名</label>
                            <input type="text"  id="name" name="name" value="${query.name }" class="form-control"/>
                        </div>

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
                            <th  data-visible="false" data- data-field="id" >ID</th>
                             <th data-field="teacher_number" >教职工编号</th>
                             <th data-field="name" >姓名</th>
                             <th data-field="sex" >性别</th>
                             <th data-field="id_card" >身份证号</th>
                             <th data-field="birth" >出生日期</th>
                             <th data-field="native_place" >籍贯</th>
                             <th data-field="email" >邮箱</th>
                             <th data-field="phone" >手机号</th>
                             <th data-field="college" >院/系</th>
                             <%--<th data-field="password" >密码</th>--%>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list }" var="n" varStatus="idx">
                            <tr data-index="${idx.index }" data-id="${n.id }">
                                <td class="bs-checkbox"><input data-index="0" name="btSelectItem" type="checkbox"></td>
                                <td data-visible="false" >${n.id }</td>
                                 <td >${n.teacherNumber}</td>
                                 <td >${n.name}</td>
                                 <td >${n.sex}</td>
                                 <td >${n.idCard}</td>
                                 <td > <fmt:formatDate value="${n.birth }" pattern="yyyy-MM-dd"/></td>
                                 <td >${n.nativePlace}</td>
                                 <td >${n.email}</td>
                                 <td >${n.phone}</td>
                                 <td >${n.college}</td>
                                 <%--<td >${n.password}</td>--%>
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
            content: "${ctx }/admin/teacher/detail",
            btn: ['确定', '取消'],
            yes: function(index, layero){ //或者使用btn1
                var detailForm = layer.getChildFrame('form', index);
                console.log(detailForm);
                var teacherNumber = $('#teacherNumber', detailForm).val();
                if(!!!teacherNumber){error('请输入教职工编号.');return false;}
                var name = $('#name', detailForm).val();
                if(!!!name){error('请输入姓名.');return false;}
                var email = $('#email', detailForm).val();

                var nativePlace = $('#nativePlace', detailForm).val();
                if(!!!nativePlace){error('请输入籍贯.');return false;}

                var sex = $('#sex', detailForm).val();
                if(!!!sex){error('请输入性别.');return false;}

                var idCard = $('#idCard', detailForm).val();
                if(!!!idCard){error('请输入身份证号.');return false;}

                if(!IdentityCodeValid(idCard)){
                    error('请输入正确身份证号码.');return false;
                }

                var birth = $('#birth', detailForm).val();
                if(!!!birth){error('请输入出生日期.');return false;}

                var phone = $('#phone', detailForm).val();
                if(!!!phone){error('请输入电话号码.');return false;}

                var phoneReg = /^1[3,5,8]\d{9}$/;
                if(!phoneReg.test(phone)){
                    error('请输入正确的电话号码.');
                    return false;
                }

                var college = $('#college', detailForm).val();
                if(!!!college){error('请输入院/系.');return false;}



                if(!!!email){error('请输入邮箱.');return false;}
                var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

                if(!emailReg.test(email)) {
                    error('请输入正确的邮箱.');
                    return false;
                }

                var password=$('#password',detailForm).val();
                if(!!!password){error('请输入密码.');return false;}

                var confirm=$('#confirm',detailForm).val();
                if(!!!confirm){error('请确认密码.');return false;}

                if(password!=confirm){
                    error('两次密码不一致.');return false;
                }

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
        k.layer.edit("${ctx }/admin/teacher/detail?id=" + id);
    }
    var page = "${page.page}";
    function reload() {
        page_submit(page);
    }
    <%--function del() {--%>
        <%--k.layer.del("${ctx }/admin/teacher/delete");--%>
    <%--}--%>
    paging('pagination', ${page.totalPages}, ${page.page});

    function IdentityCodeValid(code) {
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";

        if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
            tip = "身份证号格式错误";
            return false;
        }

        else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            return false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    return false;
                }
            }
        }
        return true;
    }



</script>
</body>