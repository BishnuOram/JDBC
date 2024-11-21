package org.example.simpleCURDOperation;

import java.sql.*;

public class InsertingDataClass {

    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

//            String query="insert into student(name,age,marks) values('%s',%d,%f)","Sahil",30,49.3;
//            ResultSet resultset=statement.executeQuery(query);

            int rowAffected = statement.executeUpdate("insert into student(name,age,marks) values('Sahil',30,49.3)");
            if (rowAffected > 0) {
                System.out.println("data updated successfully");
            } else {
                System.out.println("data not inserted");
            }
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}