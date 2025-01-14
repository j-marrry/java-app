<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='../styles/login.css' %>
</style>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<div class="flex-container">
    <div class="form-container">
        <h1 class="form-header">ВХОД В АККАУНТ</h1>
        <form action="${pageContext.request.contextPath}/login.jhtml" class="login-form" method="post">
            <div class="label-and-text">
                <label for="login" class="input-label">ЛОГИН</label>
                <input name="login" id="login" class="input-field" required>
            </div>
            <div class="label-and-text">
                <label for="password" class="input-label">ПАРОЛЬ</label>
                <input name="password" id="password" type="password" class="input-field" required>
            </div>
            <div class="error-message">${errorMessage}</div>
            <input type="submit" name="submit" value="ВХОД" class="button-submit">
        </form>
    </div>
</div>
</body>
</html>
