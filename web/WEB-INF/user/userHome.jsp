<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.FriendRequest" %>
<%@ page import="manager.FriendRequestManager" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 19.02.2019
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<% User user = (User) session.getAttribute("user");
   FriendRequestManager friendRequestManager = (FriendRequestManager) session.getAttribute("friendRequestManager");
    List<User> users = (List<User>) request.getAttribute("users");
    List<User> acceptedFriends = (List<User>) request.getAttribute("acceptedFriends");
    List<User> friendRequests = (List<User>) request.getAttribute("friendRequests");
   List<User> allFriendsBeforeDelete = (List<User>) request.getAttribute("allFriendsBeforeDelete");
    if(user != null){ %>
        <img src="/getAvatar?avatar=<%=user.getAvatar()%>" width="200"/> <br><br>
        <%=user.getName()%> <%=user.getSurname()%>
<% } %>

</br></br>All Users:
<table border="1">
    <tr>
        <td>id</td>
        <td>Photo</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Action</td>
    </tr>
    <% for (User user1 : users) { %>
    <tr>
        <td><%=user1.getId()%></td>
        <td><img src="/getAvatar?avatar=<%=user1.getAvatar()%>" width="50"/></td>
        <td><%=user1.getName()%></td>
        <td><%=user1.getSurname()%></td>
        <td><%=user1.getEmail()%></td>
        <td><a href="/user/addFriendRequest?friendId=<%=user1.getId()%>">Send friend request</a></td>
    </tr>
    <% } %>
</table>
</br></br>Friend Requests:
<table border="1">
    <tr>
        <td>id</td>
        <td>Photo</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Action</td>
    </tr>
    <% for (User friendReq : friendRequests) { %>
    <tr>
        <td><%=friendReq.getId()%></td>
        <td><img src="/getAvatar?avatar=<%=friendReq.getAvatar()%>" width="50"/></td>
        <td><%=friendReq.getName()%></td>
        <td><%=friendReq.getSurname()%></td>
        <td><%=friendReq.getEmail()%></td>
        <td><a href="/user/acceptFriendRequest?acceptId=<%=friendReq.getId()%>">Accept</a>
            <a href="/user/rejectFriendRequest?rejectId=<%=friendReq.getId()%>">Reject</a></td>
    </tr>
    <% } %>
</table>
</br></br>Friends:
<table border="1">
    <tr>
        <td>id</td>
        <td>Photo</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Action</td>
    </tr>
    <% for (User user2 : allFriendsBeforeDelete) { %>
    <tr>
        <td><%=user2.getId()%></td>
        <td><img src="/getAvatar?avatar=<%=user2.getAvatar()%>" width="50"/></td>
        <td><%=user2.getName()%></td>
        <td><%=user2.getSurname()%></td>
        <td><%=user2.getEmail()%></td>
        <td><a href="/user/deleteFriend?removeId=<%=user2.getId()%>">Delete friendship</a></td>
        <td><a href="/user/sendMessage?id=<%=user2.getId()%>">Send Message</a></td>
    </tr>
    <% } %>
</table>

</body>
</html>
