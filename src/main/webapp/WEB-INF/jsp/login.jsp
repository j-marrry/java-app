<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
    <%@include file='../styles/login.css' %>
</style>
<html>
<head>
    <title><spring:message code="login.title"/></title>
</head>
<body>
<div class="flex-container">
    <div class="form-container">
        <h1 class="form-header"><spring:message code="login.header"/></h1>
        <form action="${pageContext.request.contextPath}/login.jhtml" class="login-form" method="post">
            <div class="label-and-text">
                <label for="login" class="input-label"><spring:message code="login.login"/></label>
                <input name="login" id="login" class="input-field" required>
            </div>
            <div class="label-and-text">
                <label for="password" class="input-label"><spring:message code="login.password"/></label>
                <input name="password" id="password" type="password" class="input-field" required>
            </div>
            <div class="error-message">${errorMessage}</div>
            <button type="submit" name="submit" value="ВХОД" class="button-submit"><spring:message code="login.submit"/></button>
        </form>
        <div class="language-switcher">
            <a href="?lang=ru">
                <img src="${pageContext.request.contextPath}/images/ru-flag.png" alt="RU" width="30" height="20">
            </a>
            <a href="?lang=en">
                <img src="${pageContext.request.contextPath}/images/en-flag.png" alt="EN" width="30" height="20">
            </a>
        </div>
    </div>
</div>
</body>
</html>
