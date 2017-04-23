
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create New User Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <div class="row main">
        <div class="main-login main-center">
            <h4>Add new User Form:</h4>
            <form method="post" action="./update">
                <div class="form-group">
                    <label for="username" class="cols-sm-2 control-label">Username</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input type="hidden" name="userId" id="userId" value="${user.userId}"/>
                            <input type="text" class="form-control" name="userName" id="userName"  placeholder="Enter your Username" value="${user.userName}"/>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-success btn-lg">Create User</button>
            </form>
        </div>
    </div>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
