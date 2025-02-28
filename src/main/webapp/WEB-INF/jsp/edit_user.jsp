<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
    <%@include file='../styles/add-edit.css' %>
</style>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:myhtml title="Редактирование пользователя">
    <jsp:attribute name="body">
        <h1 class="form-header">
            Редактирование пользователя
        </h1>
        <form:form action="${pageContext.request.contextPath}/edituser.jhtml" method="post" class="edit-add_form" modelAttribute="user">
            <form:hidden path="id"/>

            <label for="username" class="input-label">Логин:</label>
            <form:input path="username" id="username" pattern="[A-Za-z0-9]{5,20}" title="Только английские буквы и цифры, 5-20 символов" required="true" class="input-field"/>
            <br>

            <label for="password" class="input-label">Пароль:</label>
            <form:input path="password" id="password" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="Пароль должен содержать буквы и цифры, 8-20 символов" required="true" class="input-field"/>
            <br>

            <label for="email" class="input-label">Email:</label>
            <form:input path="email" type="email" id="email" pattern="[a-zA-Z0-9._%+-]+@(mail\.ru|gmail\.com)" title="Введите корректный email" required="true" class="input-field"/>
            <br>

            <label for="lastname" class="input-label">Фамилия:</label>
            <form:input path="lastname" id="lastname" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required="true" class="input-field"/>
            <br>

            <label for="firstname" class="input-label">Имя:</label>
            <form:input path="firstname" id="firstname" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required="true" class="input-field"/>
            <br>

            <label for="patronymic" class="input-label">Отчество:</label>
            <form:input path="patronymic" id="patronymic" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required="true" class="input-field"/>
            <br>

            <label for="birthday" class="input-label">Дата рождения:</label>
            <form:input path="birthday" type="date" id="birthday" min="1925-12-31" max="2010-12-31" required="true" class="input-field"/>
            <br>

            <label class="input-label">Роли:</label>
            <form:checkboxes path="roles" items="${rolesList}" class="checkbox-group"/>
            <p class="hint">Вы можете выбрать несколько ролей.</p>
            <br>

            <div class="error-message">${errorMessage}</div>

            <button type="submit" class="button">Сохранить</button>
            <br>
            <a href="${pageContext.request.contextPath}/edituser.jhtml?username=${user.username}&action=delete" class="delete">Удалить пользователя</a>
        </form:form>
    </jsp:attribute>
</t:myhtml>

