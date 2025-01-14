<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="role" required="true" type="java.lang.String" %>

<div class="menu">
    <a href="${pageContext.request.contextPath}/welcome.jhtml?action=main" class="menu-item-mainpage">ГЛАВНАЯ</a>
    <a href="${pageContext.request.contextPath}/welcome.jhtml?action=passchange">СМЕНИТЬ ПАРОЛЬ</a>
    <c:choose>
        <c:when test="${role.equals('admin')}">
            <a href="${pageContext.request.contextPath}/welcome.jhtml?action=users" class="menu-item-userspage">ПОЛЬЗОВАТЕЛИ</a>
        </c:when>
    </c:choose>
</div>