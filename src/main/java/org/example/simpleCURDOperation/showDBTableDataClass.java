package org.example.simpleCURDOperation;

import java.sql.*;

public class showDBTableDataClass {
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
            String query = "select * from student";

            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                int age = resultset.getInt("age");
                double marks = resultset.getDouble("marks");
                System.out.println("Id is :" + id);
                System.out.println("Name is :" + name);
                System.out.println("Age is :" + age);
                System.out.println("Marks is :" + marks);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }