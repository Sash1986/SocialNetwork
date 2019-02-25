package servlet;

import manager.FriendRequestManager;
import manager.UserManager;
import model.Action;
import model.FriendRequest;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/addFriendRequest")
public class AddFriendRequestServlet extends HttpServlet {

    FriendRequestManager friendRequestManager = new FriendRequestManager();
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        String friendId = req.getParameter("friendId");

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setUserId(user.getId());
        friendRequest.setFriendId(Integer.parseInt(friendId));
        friendRequest.setAction(Action.SEND);
        friendRequestManager.addFriend(friendRequest);

        resp.sendRedirect("/user/userHome");
    }
}
