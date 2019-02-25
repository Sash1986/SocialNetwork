package manager;

import db.DBConnectionProvider;
import model.Messages;
import model.User;
import util.DateUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessagesManager {

    private Connection connection;

    public MessagesManager() {

        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addMessage(Messages message) {
        try {
            String query = "INSERT INTO message(`text`,`created_date`,`user_id`,`user_name`,`friend_id`,`friend_name`, `attachedFile`) " +
                    "VALUES(?,?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, message.getText());
            statement.setString(2, DateUtil.convertDateToString(message.getCreatedDate()));
            statement.setInt(3, message.getUserId());
            statement.setString(4, message.getUserName());
            statement.setInt(5, message.getFriendId());
            statement.setString(6, message.getFriendName());
            statement.setString(7, message.getAttachedFile());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                message.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Messages> getUsersMessage(int userId, int friendId) {
        String query = "SELECT * FROM `message` WHERE user_id IN("+userId+","+friendId+ ") AND `friend_id` IN(" +userId+","+friendId+");";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Messages> messages = new LinkedList<Messages>();
            while (resultSet.next()) {
                Messages message = new Messages();
                message.setId(resultSet.getInt(1));
                message.setText(resultSet.getString(2));
                message.setCreatedDate(DateUtil.convertStringToDate(resultSet.getString(3)));
                message.setUserId(resultSet.getInt(4));
                message.setUserName(resultSet.getString(5));
                message.setFriendId(resultSet.getInt(6));
                message.setFriendName(resultSet.getString(7));
                message.setAttachedFile(resultSet.getString(8));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getFriendById(int frId) {

        try {
            String query = "SELECT * FROM user WHERE id = " + frId;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setAvatar(resultSet.getString(6));

                return (user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
