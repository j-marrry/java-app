<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="roles" required="true" type="java.lang.String" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="menu">
    <a href="${pageContext.request.contextPath}/welcome.jhtml?action=main" class="menu-item-mainpage"><spring:message code="menu.main"/></a>
    <a href="${pageContext.request.contextPath}/welcome.jhtml?action=passchange"><spring:message code="menu.passchange"/></a>
    <c:choose>
        <c:when test="${roles.contains('admin')}">
            <a href="${pageContext.request.contextPath}/welcome.jhtml?action=users" class="menu-item-userspage"><spring:message code="menu.users"/></a>
        </c:when>
    </c:choose>
    <div class="language-switcher">
        <a href="?lang=ru">
            <img src="${pageContext.request.contextPath}/images/ru-flag.png" alt="RU" width="30" height="20">
        </a>
        <a href="?lang=en">
            <img src="${pageContext.request.contextPath}/images/en-flag.png" alt="EN" width="30" height="20">
        </a>
    </div>
</div>