<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


                <c:forEach items="${rootMenu.children }" var="level1Menu">

                    <c:if test="${not level1Menu.deleted }">
                        <c:if test="${level1Menu.show }">

                        <li>
                            <a  href="${ctx }/${level1Menu.href}"  <c:if test="${level1Menu.href!='#' }">class="J_menuItem"</c:if>>
                                <i class="${not empty level1Menu.icon ? level1Menu.icon : 'fa fa-home' }"></i>
                                <span class="nav-label">${level1Menu.name }</span>
                                <span class="fa arrow"></span>
                            </a>
                            <ul class="nav nav-second-level">
                                <c:forEach items="${level1Menu.children }" var="level2Menu">
                                    <c:if test="${not level2Menu.deleted }">
                                        <c:if test="${level2Menu.show }">
                                        <li>
                                            <a class="J_menuItem" href="${ctx }/${level2Menu.href}"><c:if test="${not empty level2Menu.icon }">
                                            <i class="${level2Menu.icon}"></i>
                                            </c:if>${level2Menu.name }</a>
                                        </li>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </ul>

                        </li>
                        </c:if>
                    </c:if>
                </c:forEach>
