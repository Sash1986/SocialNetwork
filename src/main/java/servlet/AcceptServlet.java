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

@WebServlet(urlPatterns = "/user/acceptFriendRequest")
public class AcceptServlet extends HttpServlet {

    FriendRequestManager friendRequestManager = new FriendRequestManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String acceptId = req.getParameter("acceptId");
        User user = (User) session.getAttribute("user");

        friendRequestManager.acceptUserById(user.getId(), Integer.parseInt(acceptId));
        req.getRequestDispatcher("/user/userHome").forward(req, resp);
    }
}
