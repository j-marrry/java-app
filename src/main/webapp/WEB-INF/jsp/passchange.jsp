<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
    <%@include file='../styles/passchange.css' %>
</style>
<html>
<head>
    <title>Смена пароля</title>
</head>
<body>
<div class="flex-container">
    <div class="form-container">
        <h1 class="form-header">СМЕНА ПАРОЛЯ</h1>
        <form:form modelAttribute="passwordChangeForm" action="${pageContext.request.contextPath}/passchange.jhtml" method="post" class="passchange-form">
            <div class="label-and-text">
                <label for="oldpass" class="input-label">СТАРЫЙ ПАРОЛЬ</label>
                <form:input path="oldPassword" id="oldpass" type="password" required="true" class="input-field"/>
            </div>
            <div class="label-and-text">
                <label for="newpass" class="input-label">НОВЫЙ ПАРОЛЬ</label>
                <form:input path="newPassword" id="newpass" type="password" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="Пароль должен содержать буквы и цифры, 8-20 символов" required="true" class="input-field"/>
            </div>
            <div class="label-and-text">
                <label for="reppass" class="input-label">ПОВТОР ПАРОЛЯ</label>
                <form:input path="confirmPassword" id="reppass" type="password" required="true" class="input-field"/>
            </div>
            <div class="error-message">${errorMessage}</div>
            <div class="buttons-container">
                <input type="submit" name="action" value="СМЕНИТЬ" class="button">
                <input type="submit" name="action" value="ГЛАВНАЯ" class="button">
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
