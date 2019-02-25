package servlet;

import manager.MessagesManager;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/sendMessage")
public class MessageServlet extends HttpServlet {
    MessagesManager messageManager = new MessagesManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        String messageId = req.getParameter("id");

        req.setAttribute("usersMessage", messageManager.getUsersMessage(user.getId(), Integer.parseInt(messageId)));
        req.setAttribute("messageToFriend", messageManager.getFriendById(Integer.parseInt(messageId)));
        req.getRequestDispatcher("/WEB-INF/user/newMessage.jsp").forward(req, resp);

    }
}
