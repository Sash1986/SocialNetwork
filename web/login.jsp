<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.02.2019
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<% if(request.getAttribute("message") != null) {%>
<p style="color: red"><%= request.getAttribute("message") %></p>
<% } %>
<form action="/login" method="post">
    Email: <input type="text" name="email" /><br>
    Password: <input type="password" name="password" /><br>
    <input type="submit" value="Login">
    <a href="/registration.jsp"><input type="button" value="Registration"></a>
</form>
</body>
</html>
