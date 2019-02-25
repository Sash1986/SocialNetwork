<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.02.2019
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/registration" method="post" enctype="multipart/form-data">
    Name: <input type="text" name="name"></br><br>
    Surname: <input type="text" name="surname"></br></br>
    Email: <input type="text" name="email"></br></br>
    Password: <input type="password" name="password"></br></br>
    Image: <input type="file" name="avatar"/><br><br>

    <input type="submit" value="Registration">
</form>
</body>
</html>
