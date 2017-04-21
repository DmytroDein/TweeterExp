<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Twitter users list</title>
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>

</head>
<body>
    <p>All Users list:</p>
    <p>Users list size: ${users.size()}</p><br/>
    <%--<c:forEach var="usr" items="${users}">
        User: <c:out value="${usr}"/><br/>
    </c:forEach>--%>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>User id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usr" items="${users}">
            <tr>
                <th scope="row">${usr.userId}</th>
                <td>---</td>
                <td>---</td>
                <td><c:out value="${usr.userName}"/></td>
                <%--<td><button type="button" class="btn btn-success">Edit</button></td>--%>
                <td><a href="./edit/${usr.userId}" class="btn btn-success">Edit User</a></td>
            </tr>
        </c:forEach>
        </tbody>
        <%--<p class="bg-warning"><button type="button" class="btn btn-warning btn-lg">Add User</button></p>--%>
        <a href="./add" class="btn btn-warning btn-lg" role="button">Add New User</a>

    </table>



    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
