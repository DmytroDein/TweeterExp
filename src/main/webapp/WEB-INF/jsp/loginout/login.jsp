
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Custom Login</title>
</head>
<body>
    <form action="/web/app/login" method="POST">
        <%--<p>Name:</p><input id="username" type="text" name="username">--%>
            <p>Name:</p><input id="username" type="text" name="username">
        <br />
        <p>Password:</p><input id="password" type="password" name="password" />
        <input type="submit" value="Login" />
    </form>
</body>
</html>