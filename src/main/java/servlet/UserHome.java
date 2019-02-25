package servlet;

import manager.FriendRequestManager;
import manager.UserManager;
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

@WebServlet(urlPatterns = "/user/userHome")
public class UserHome extends HttpServlet {

    private UserManager userManager = new UserManager();
    private  FriendRequestManager friendRequestManager = new  FriendRequestManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");


        if (user == null){
            resp.sendRedirect("/index.jsp");
        }else {

            req.setAttribute("users", userManager.getAllUsersByIdWithoutCurrentUser(user.getId()));
            req.setAttribute("acceptedFriends", friendRequestManager.getAllAcceptedRequests());
            req.setAttribute("friendRequests", friendRequestManager.getAllRequestToCurrentUser(user.getId()));
            req.setAttribute("allFriendsBeforeDelete", friendRequestManager.getAllFriends(user.getId()));
            req.getRequestDispatcher("/WEB-INF/user/userHome.jsp").forward(req, resp);
        }
    }
}
