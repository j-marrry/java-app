<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% String params = request.getQueryString();
    if (params != null) {
        params = params.replaceAll("(^|&)lang=[^&]*", "").replaceAll("^&", "");
    } else {
        params = "";
    }
    String queryPrefix = params.isEmpty() ? "" : params + "&";%>
<div class="menu">
    <a href="${pageContext.request.contextPath}/welcome.jhtml?action=main" class="menu-item-mainpage"><spring:message code="menu.main"/></a>
    <a href="${pageContext.request.contextPath}/welcome.jhtml?action=passchange"><spring:message code="menu.passchange"/></a>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="${pageContext.request.contextPath}/welcome.jhtml?action=users" class="menu-item-userspage">
            <spring:message code="menu.users"/>
        </a>
    </sec:authorize>
    <div class="language-switcher">
        <a href="?<%= queryPrefix %>lang=ru">
            <img src="${pageContext.request.contextPath}/images/ru-flag.png" alt="RU" width="30" height="20">
        </a>
        <a href="?<%= queryPrefix %>lang=en">
            <img src="${pageContext.request.contextPath}/images/en-flag.png" alt="EN" width="30" height="20">
        </a>
    </div>
</div>