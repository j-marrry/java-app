<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
    <%@include file='../styles/add-edit.css' %>
</style>
<c:set var="localizedMsgValidUsername"><spring:message code="add-edit.valid-username"/></c:set>
<c:set var="localizedMsgValidPassword"><spring:message code="add-edit.valid-pass"/></c:set>
<c:set var="localizedMsgValidEmail"><spring:message code="add-edit.valid-email"/></c:set>
<c:set var="localizedMsgValidFIO"><spring:message code="add-edit.valid-fio"/></c:set>
<c:set var="localizedTitle"><spring:message code="add.title"/></c:set>
<t:myhtml title="${localizedTitle}">
    <jsp:attribute name="body">
        <h1 class="form-header"><spring:message code="add.header"/></h1>
        <form:form modelAttribute="user" action="${pageContext.request.contextPath}/adduser.jhtml" method="post" class="edit-add_form">
            <label for="username" class="input-label"><spring:message code="add-edit.username"/>:</label>
            <form:input path="username" pattern="[A-Za-z0-9]{5,20}" title="${localizedMsgValidUsername}" required="true" class="input-field"/>
            <br>

            <label for="password" class="input-label"><spring:message code="add-edit.password"/>:</label>
            <form:input path="password" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="${localizedMsgValidPassword}" required="true" class="input-field"/>
            <br>

            <label for="email" class="input-label"><spring:message code="add-edit.email"/>:</label>
            <form:input path="email" type="email" pattern="[a-zA-Z0-9._%+-]+@(mail\.ru|gmail\.com)" title="${localizedMsgValidEmail}" required="true" class="input-field"/>
            <br>

            <label for="lastname" class="input-label"><spring:message code="add-edit.lastname"/>:</label>
            <form:input path="lastname" pattern="[А-Яа-яЁёA-Za-z]{2,25}" title="${localizedMsgValidFIO}" required="true" class="input-field"/>
            <br>

            <label for="firstname" class="input-label"><spring:message code="add-edit.firstname"/>:</label>
            <form:input path="firstname" pattern="[А-Яа-яЁёA-Za-z]{2,25}" title="${localizedMsgValidFIO}" required="true" class="input-field"/>
            <br>

            <label for="patronymic" class="input-label"><spring:message code="add-edit.patronymic"/>:</label>
            <form:input path="patronymic" pattern="[А-Яа-яЁёA-Za-z]{2,25}" title="${localizedMsgValidFIO}" class="input-field"/>
            <br>

            <label for="birthday" class="input-label"><spring:message code="add-edit.birthdate"/>:</label>
            <form:input path="birthday" type="date" required="true" class="input-field"/>
            <br>

            <label class="input-label"><spring:message code="add-edit.roles"/>:</label>
            <form:checkboxes path="roles" items="${rolesList}" class="checkbox-group"/>
            <p class="hint"><spring:message code="add-edit.msg"/>.</p>
            <br>

            <div class="error-message">${errorMessage}</div>

            <button type="submit" class="button" name="action" value="Добавить"><spring:message code="add.save"/></button>
        </form:form>
    </jsp:attribute>
</t:myhtml>
