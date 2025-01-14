<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file='../styles/welcome.css' %>
</style>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<div class="flex-container">
    <div class="header">
        <div class="logo">
            LOGO
        </div>
        <div class="cont">
            <div class="hello-text">
                Привет, ${username}
            </div>
            <form action="${pageContext.request.contextPath}/welcome.jhtml" method="post">
                <input type="hidden" name="action" value="ВЫЙТИ">
                <button type="submit" class="button logout">ВЫЙТИ</button>
            </form>
        </div>
    </div>
    <t:menu role="${sessionScope.role}" />
</div>
</body>
</html>