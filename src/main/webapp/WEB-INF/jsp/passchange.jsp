<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
    <%@include file='../styles/passchange.css' %>
</style>
<c:set var="localizedMsgValidPass"><spring:message code="passchange.valid-pass"/></c:set>
<html>
<head>
    <title><spring:message code="passchange.title"/></title>
</head>
<body>
<div class="flex-container">
    <div class="form-container">
        <h1 class="form-header"><spring:message code="passchange.header"/></h1>
        <form:form modelAttribute="passwordChangeForm" action="${pageContext.request.contextPath}/passchange.jhtml" method="post" class="passchange-form">
            <div class="label-and-text">
                <label for="oldpass" class="input-label"><spring:message code="passchange.old-pass"/></label>
                <form:input path="oldPassword" id="oldpass" type="password" required="true" class="input-field"/>
            </div>
            <div class="label-and-text">
                <label for="newpass" class="input-label"><spring:message code="passchange.new-pass"/></label>
                <form:input path="newPassword" id="newpass" type="password" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="${localizedMsgValidPass}" required="true" class="input-field"/>
            </div>
            <div class="label-and-text">
                <label for="reppass" class="input-label"><spring:message code="passchange.conf-pass"/></label>
                <form:input path="confirmPassword" id="reppass" type="password" required="true" class="input-field"/>
            </div>
            <div class="error-message">${errorMessage}</div>
                <input type="submit" name="action" value=<spring:message code="passchange.change"/> class="button">
            <a href="${pageContext.request.contextPath}/welcome.jhtml" class="main"><spring:message code="passchange.main"/></a>
        </form:form>
    </div>
</div>
</body>
</html>
