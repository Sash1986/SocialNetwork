<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.02.2019
  Time: 2:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<c:forEach var="message" items="${requestScope.get('usersMessage')}">
    <h4>
            <div style="color: darkgrey" >${message.createdDate}</div><br>
            ${message.userName} :
    </h4>
    <h3 style="color: maroon">
            ${message.text} <br>
        <c:if test="${message.attachedFile != null && message.attachedFile ne ' '}">
            <div>
                <img src="/getAvatar?avatar=${message.attachedFile}" width="200"/>
            </div>
        </c:if>
    </h3>

</c:forEach>
<form action="/user/message" method="post" enctype="multipart/form-data">
    <input type="hidden" name="friendId" value="<c:out value="${requestScope.messageToFriend.id}"/>"><br>
    <input type="hidden" name="friendName" value="<c:out value="${requestScope.messageToFriend.name}"/>"><br>

    <input type="text" name="text">
    <input type="file" name="attachedFile">
    <br><br>
    <input type="submit" value="Message"><br><br>
    <a href="/user/userHome">Home</a>
</form>

</body>
</html>
