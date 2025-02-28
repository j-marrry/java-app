<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
    <%@include file='../styles/add-edit.css' %>
</style>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:myhtml title="Добавление пользователя">
    <jsp:attribute name="body">
        <h1 class="form-header">Добавление пользователя</h1>
        <form:form modelAttribute="user" action="${pageContext.request.contextPath}/adduser.jhtml" method="post" class="edit-add_form">
            <label for="username" class="input-label">Логин:</label>
            <form:input path="username" pattern="[A-Za-z0-9]{5,20}" title="Только английские буквы и цифры, 5-20 символов" required="true" class="input-field"/>
            <br>

            <label for="password" class="input-label">Пароль:</label>
            <form:input path="password" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="Пароль должен содержать буквы и цифры, 8-20 символов" required="true" class="input-field"/>
            <br>

            <label for="email" class="input-label">Email:</label>
            <form:input path="email" type="email" pattern="[a-zA-Z0-9._%+-]+@(mail\.ru|gmail\.com)" title="Введите корректный email" required="true" class="input-field"/>
            <br>

            <label for="lastname" class="input-label">Фамилия:</label>
            <form:input path="lastname" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required="true" class="input-field"/>
            <br>

            <label for="firstname" class="input-label">Имя:</label>
            <form:input path="firstname" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required="true" class="input-field"/>
            <br>

            <label for="patronymic" class="input-label">Отчество:</label>
            <form:input path="patronymic" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required="true" class="input-field"/>
            <br>

            <label for="birthday" class="input-label">Дата рождения:</label>
            <form:input path="birthday" type="date" required="true" class="input-field"/>
            <br>

            <label class="input-label">Роли:</label>
            <form:checkboxes path="roles" items="${rolesList}" class="checkbox-group"/>
            <p class="hint">Вы можете выбрать несколько ролей.</p>
            <br>

            <div class="error-message">${errorMessage}</div>

            <button type="submit" class="button" name="action" value="Добавить">Добавить</button>
        </form:form>
    </jsp:attribute>
</t:myhtml>
