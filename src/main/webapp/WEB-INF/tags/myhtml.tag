<%@ attribute name="title" %>
<%@ attribute name="body" fragment="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<div class="flex-container">
    <t:menu/>
    <jsp:invoke fragment="body" />
</div>
</body>
</html>
