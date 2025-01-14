<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file='../styles/users.css' %>
</style>
<t:myhtml title="Список пользователей">
    <jsp:attribute name="body">
        <h1 class="form-header">Список пользователей</h1>
        <div class="users">
            <a href="${pageContext.request.contextPath}/adduser.jhtml" class="add">Добавить пользователя</a>
            <table>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/edituser.jhtml?username=${user.username}" class="a">${user.username}</a></td>
                    </tr>
                    <tr>
                        <td>${user.lastname} ${user.firstname}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:attribute>
</t:myhtml>
