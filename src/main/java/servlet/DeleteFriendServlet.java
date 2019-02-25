package servlet;

import manager.FriendRequestManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/deleteFriend")
public class DeleteFriendServlet extends HttpServlet {
    FriendRequestManager friendRequestManager = new FriendRequestManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String removeId = req.getParameter("removeId");
        User user = (User) session.getAttribute("user");
        friendRequestManager.removeUserById(user.getId(), Integer.parseInt(removeId));
        resp.sendRedirect("/user/userHome");
    }
}
