package org.example.batchpatching;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInputInBPClass {
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
            String query = "UPDATE student SET name=? WHERE id=? ";
            PreparedStatement preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, "John");
            preparedstatement.setInt(2, 109);
            preparedstatement.addBatch();

            preparedstatement.setString(1, "Mukesh Dudum");
            preparedstatement.setInt(2, 100);
            preparedstatement.addBatch();

            int[] arrcount = preparedstatement.executeBatch();
                if(arrcount.length>0){
                    System.out.println("Inserted..");
            }else{
                    System.out.println("Not inserted..");
                }
            preparedstatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
