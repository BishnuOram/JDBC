package org.example.anp;

import java.sql.*;
import java.util.Scanner;

class User{
    private int id;
    private String name;
    private  String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
interface UserDao{
    void addUser(User user) throws SQLException;
    User getUser(int userId) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int userId) throws SQLException;
}

class UserDaoTmpl implements UserDao {
    private static final String url = "jdbc:mysql://localhost:3306/anudip";
    private static final String username = "root";
    private static final String password = "root";

    public void addUser(User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (name, email) VALUES (?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        finally {
            {
                System.out.println("Insert data successfully");
            }
        }

    }
    @Override
    public User getUser(int userId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?")) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    return user;
                }
            }
        }
        return null;
    }
//    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
@Override
public void updateUser(User user) throws SQLException {
    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET name = ?, email = ? WHERE id = ?")) {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setInt(3, user.getId());
        preparedStatement.executeUpdate();
        System.out.println("Update data successfully");
    }
}
    @Override
    public void deleteUser(int userId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id = ?")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Delete data successfully");
        }
    }
}
public class UserDaoTest {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        try {
            UserDao userDao = new UserDaoTmpl();
            // For Adding user data
            User user1 = new User();
            user1.setName("Bishnu Oram");
            user1.setEmail("bishnu@gmail.com");
            userDao.addUser(user1);

            User user2 = new User();
            user2.setName("Mukesh Kumar");
            user2.setEmail("mukesh@gmail.com");
            userDao.addUser(user2);

            //For Retrieve user data
            User retrievedUser = userDao.getUser(1);
            System.out.println("Retrieved User: " + retrievedUser.getName() + ", " + retrievedUser.getEmail());

            // For Update user data
            retrievedUser.setName("Pabitra Bera");
            retrievedUser.setEmail("pabitrabera@gmail.com");
            userDao.updateUser(retrievedUser);

            // Retrieve updated user
            User updatedUser = userDao.getUser(1);
            System.out.println("Updated User: " + updatedUser.getName() + ", " + updatedUser.getEmail());

            // For Delete user data
            userDao.deleteUser(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//Output :
//Insert data successfully
//Insert data successfully
//Retrieved User: Bishnu Oram, bishnu@gmail.com
//Update data successfully
//Updated User: Pabitra Bera, pabitrabera@gmail.com
//Delete data successfully