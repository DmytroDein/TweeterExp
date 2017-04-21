
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<p>Error caused</p>
<p><c:out value="${ex}"/></p>
<p><c:out value="${err}"/></p>
</body>
</html>
