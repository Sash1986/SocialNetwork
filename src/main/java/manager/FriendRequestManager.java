package manager;

import db.DBConnectionProvider;
import model.Action;
import model.FriendRequest;
import model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FriendRequestManager {
    private Connection connection;

    public FriendRequestManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addFriend(FriendRequest friendRequest) {
        try {
            String query = "INSERT INTO friend_request(`user_id`,`friend_id`,`action`)" +
                    "VALUES(?,?,?);";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, friendRequest.getUserId());
            statement.setInt(2, friendRequest.getFriendId());
            statement.setString(3, friendRequest.getAction().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllRequestToCurrentUser(int currUserId) {

        String query = "SELECT `user`.* FROM `friend_request` AS fr, `user` " +
                        " WHERE fr.`user_id` = `user`.`id` " +
                        " AND fr.`friend_id` = " + currUserId +
                        " AND `action` = 'SEND'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List <User> requests = new LinkedList <User>();
            UserManager userManager = new UserManager();
            while (resultSet.next()) {
                requests.add(userManager.getUserById(resultSet.getInt(1)));
            }
            return requests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List <User> getAllAcceptedRequests() {
        String query = "SELECT  `user`.* FROM `user`, `friend_request` " +
                        " WHERE `user`.`id` = `friend_request`.`user_id`"+
                        "AND `friend_request`.`action` = 'ACCEPT'";
        try {
            UserManager userManager = new UserManager();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List <User> requests = new LinkedList <User>();
            while (resultSet.next()) {
                requests.add(userManager.getUserById(resultSet.getInt(1)));
            }
            return requests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List <User> getAllFriends(int id) {
        String query = "SELECT  `user`.* FROM `user`, `friend_request` " +
                        " WHERE `user`.`id` = `friend_request`.`friend_id`"+
                        " AND `friend_request`.`user_id` = "+ id +
                        " AND `friend_request`.`action` = 'ACCEPT'";
        try {
            UserManager userManager = new UserManager();
            Statement statement = connection.createStatement();

            List <User> requests = new LinkedList <User>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                requests.add(userManager.getUserById(resultSet.getInt(1)));
            }
            return requests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeUserById(int userId , int friendId) {
        String query = "DELETE fr.* FROM `friend_request` AS fr " +
                        " LEFT JOIN `user` AS u ON fr.`friend_id` = " + friendId +
                        " WHERE u.`id` = "+ userId +
                        " AND fr.`action` = 'ACCEPT';";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rejectUserById(int userId , int friendId) {
        String query = "DELETE fr.* FROM `friend_request` AS fr " +
                                 " LEFT JOIN `user` AS u ON fr.`friend_id` = " + friendId +
                                  " AND u.`id` = "+ userId +
                                    " WHERE fr.`action` = 'SEND';";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void acceptUserById(int userId, int friendId) {
        String query = "UPDATE `friend_request` AS f, `user` SET f.`action` = 'ACCEPT' " +
                        " WHERE `user`.`id` = " + friendId +
                        " AND f.`friend_id` = " + userId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private FriendRequest getRequestFromDB(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return createRequestFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FriendRequest createRequestFromResultSet(ResultSet resultSet) throws SQLException {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setUserId(resultSet.getInt(1));
        friendRequest.setFriendId(resultSet.getInt(2));
        friendRequest.setAction(Action.valueOf(resultSet.getString(3)));

        return friendRequest;
    }
}
