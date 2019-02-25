package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private Connection connection;

    public UserManager() {

        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) {

        String query = "INSERT INTO user(`name`,`surname`,`email`,`password`,`avatar`) VALUES(?,?,?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getName());
            statement.setString(2,user.getSurname());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            statement.setString(5,user.getAvatar());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {

        try {
            String query = "SELECT * FROM user WHERE id = " + id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            User user = new User();

            if (resultSet.next()) {
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

    public List<User> getAllUsersByIdWithoutCurrentUser(int id) {

        try {
            String query = "SELECT * FROM user WHERE id != " + id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new LinkedList<User>();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setAvatar(resultSet.getString(6));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUserByEmailAndPassword(String email, String password) {

        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";

        try {
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

    public List<User> getAllUsers() {

        String query = "SELECT * FROM user";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new LinkedList<User>();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setAvatar(resultSet.getString(6));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void  removeUserById(int id) {
        String query = "DELETE FROM user WHERE id = " + id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
