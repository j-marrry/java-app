<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
    <%@include file='../styles/users.css' %>
</style>
<c:set var="localizedTitle"><spring:message code="users.title"/></c:set>
<t:myhtml title="${localizedTitle}">
    <jsp:attribute name="body">
        <h1 class="form-header"><spring:message code="users.header"/></h1>
        <div class="users">
            <a href="${pageContext.request.contextPath}/adduser.jhtml" class="add"><spring:message code="users.add"/></a>
            <table>
            <th><spring:message code="users.login"/></th>
            <th><spring:message code="users.lastname"/></th>
            <th><spring:message code="users.firstname"/></th>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/edituser.jhtml?username=${user.username}" class="a">${user.username}</a></td>
                        <td>${user.lastname}</td>
                        <td>${user.firstname}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:attribute>
</t:myhtml>
