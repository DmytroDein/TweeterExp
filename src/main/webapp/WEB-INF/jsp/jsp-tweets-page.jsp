
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tweets</title>
</head>
<body>
    <%--${2+2}--%>
    <p>All Tweets</p>
    <c:forEach var="tweet" items="${tweets}">
        Tweet <c:out value="${tweet}"/><p>
    </c:forEach>
</body>
</html>
