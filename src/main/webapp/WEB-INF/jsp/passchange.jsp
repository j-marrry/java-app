<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <form action="${pageContext.request.contextPath}/passchange.jhtml" method="post" class="passchange-form">
            <div class="label-and-text">
                <label for="oldpass" class="input-label">СТАРЫЙ ПАРОЛЬ</label>
                <input name="oldpass" id="oldpass" type="password" class="input-field">
            </div>
            <div class="label-and-text">
                <label for="newpass" class="input-label">НОВЫЙ ПАРОЛЬ</label>
                <input name="newpass" id="newpass" type="password" class="input-field">
            </div>
            <div class="label-and-text">
                <label for="reppass" class="input-label">ПОВТОР ПАРОЛЯ</label>
                <input name="reppass" id="reppass" type="password" class="input-field">
            </div>
            <div class="error-message">${errorMessage}</div>
            <div class="buttons-container">
                <input type="submit" name="action" value="ГЛАВНАЯ" class="button">
                <input type="submit" name="action" value="СМЕНИТЬ" class="button">
            </div>
        </form>
    </div>
</div>
</body>
</html>
