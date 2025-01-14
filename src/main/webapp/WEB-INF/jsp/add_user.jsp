<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file='../styles/add-edit.css' %>
</style>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:myhtml title="Добавление пользователя">
    <jsp:attribute name="body">
        <h1 class="form-header">Добавление пользователя</h1>
        <form action="${pageContext.request.contextPath}/adduser.jhtml" method="post" class="edit-add_form">
            <input type="hidden" id="login" name="login" value="" class="input-field">

            <label for="username" class="input-label">Логин:</label>
            <input type="text" id="username" name="username" pattern="[A-Za-z0-9]{5,20}" title="Только английские буквы и цифры, 5-20 символов" required class="input-field"><br>

            <label for="password" class="input-label">Пароль:</label>
            <input type="text" id="password" name="password" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="Пароль должен содержать буквы и цифры, 8-20 символов" required class="input-field"><br>

            <label for="email" class="input-label">Email:</label>
            <input type="email" id="email" name="email" pattern="[a-zA-Z0-9._%+-]+@(mail\.ru|gmail\.com)" title="Введите корректный email" required class="input-field"><br>

            <label for="lastname" class="input-label">Фамилия:</label>
            <input type="text" id="lastname" name="lastname" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required class="input-field"><br>

            <label for="firstname" class="input-label">Имя:</label>
            <input type="text" id="firstname" name="firstname" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required class="input-field"><br>

            <label for="patronymic" class="input-label">Отчество:</label>
            <input type="text" id="patronymic" name="patronymic" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required class="input-field"><br>

            <label for="birthday" class="input-label">Дата рождения:</label>
            <input type="date" id="birthday" name="birthday" min="1925-12-31" max="2010-12-31" required class="input-field"><br>

            <label for="role" class="input-label">Роль:</label>
            <select id="role" name="role" required class="input-field">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select><br>

            <button type="submit" class="button" name="action" value="Добавить">Добавить</button>
        </form>
    </jsp:attribute>
</t:myhtml>
