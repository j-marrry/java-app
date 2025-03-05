<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
    <%@include file='../styles/welcome.css' %>
</style>
<html>
<head>
    <title><spring:message code="welcome.title"/></title>
</head>
<body>
<div class="flex-container">
    <div class="header">
        <div class="logo">
            LOGO
        </div>
        <div class="cont">
            <div class="hello-text">
                <spring:message code="welcome.hello"/>, <sec:authentication property="principal.username"/>
            </div>
            <form action="${pageContext.request.contextPath}/welcome.jhtml" method="post">
                <input type="hidden" name="action" value="ВЫЙТИ">
                <button type="submit" class="button logout"><spring:message code="welcome.logout"/></button>
            </form>
        </div>
    </div>
    <t:menu/>
</div>
</body>
</html>