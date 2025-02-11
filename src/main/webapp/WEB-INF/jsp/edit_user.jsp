<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file='../styles/add-edit.css' %>
</style>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:myhtml title="Редактирование пользователя">
    <jsp:attribute name="body">
        <h1 class="form-header">
            Редактирование пользователя
        </h1>
        <form action="${pageContext.request.contextPath}/edituser.jhtml" method="post" class="edit-add_form">
            <input type="hidden" id="login" name="login" value="${username}" class="input-field">
            <label for="username" class="input-label">Логин:</label>
            <input type="text" id="username" name="username" value="${username}" pattern="[A-Za-z0-9]{5,20}" title="Только английские буквы и цифры, 5-20 символов" required class="input-field"><br>
            <label for="password" class="input-label">Пароль:</label>
            <input type="text" id="password" name="password" value="${password}" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="Пароль должен содержать буквы и цифры, 8-20 символов" required class="input-field"><br>
            <label for="email" class="input-label">Email:</label>
            <input type="email" id="email" name="email" value="${email}" pattern="[a-zA-Z0-9._%+-]+@(mail\.ru|gmail\.com)" title="Введите корректный email" required class="input-field"><br>
            <label for="lastname" class="input-label">Фамилия:</label>
            <input type="text" id="lastname" name="lastname" value="${lastname}" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required class="input-field"><br>
            <label for="firstname" class="input-label">Имя:</label>
            <input type="text" id="firstname" name="firstname" value="${firstname}" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required class="input-field"><br>
            <label for="patronymic" class="input-label">Отчество:</label>
            <input type="text" id="patronymic" name="patronymic" value="${patronymic}" pattern="[А-Яа-яЁё]{2,25}" title="Только русские буквы, 2-25 символов" required class="input-field"><br>
            <label for="birthday" class="input-label">Дата рождения:</label>
            <input type="date" id="birthday" name="birthday" value="${birthday}" min="1925-12-31" max="2010-12-31" required class="input-field"><br>
            <label for="roles" class="input-label">Роли:</label>
            <select id="roles" name="roles" multiple required class="input-field">
                <option value="admin" <c:forEach var="userRole" items="${roles}"><c:if test="${userRole eq 'admin'}">selected</c:if></c:forEach>>Admin</option>
                <option value="user" <c:forEach var="userRole" items="${roles}"><c:if test="${userRole eq 'user'}">selected</c:if></c:forEach>>User</option>
                <option value="manager" <c:forEach var="userRole" items="${roles}"><c:if test="${userRole eq 'manager'}">selected</c:if></c:forEach>>Manager</option>
            </select>
            <p class="hint">Вы можете выбрать несколько ролей (CTRL + клик).</p>
            <br>
            <button type="submit" class="button" name="action" value="Сохранить">Сохранить</button>
            <br>
            <a href="${pageContext.request.contextPath}/edituser.jhtml?username=${username}&action=delete" class="delete">Удалить пользователя</a>
        </form>
    </jsp:attribute>
</t:myhtml>

