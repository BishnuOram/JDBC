package org.example.simpleCURDOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class PreparedStatementClass {
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

//            String query = "delete from student where id=?";
            PreparedStatement preparedstatement = connection.prepareStatement("delete from student where id=?");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Enter id want to delete :");
                int Sid = sc.nextInt();
                System.out.println("Enter choice (Y/N)");
                String choice=sc.next();
                preparedstatement.setInt(1, Sid);
                int rowAffected = preparedstatement.executeUpdate();
                if (rowAffected > 0) {
                    System.out.println("data updated successfully");
                } else if(choice.toUpperCase().equals("N")) {
                    break;
                }else
                    System.out.println("data not inserted");
                }

//         if(resultset.next()){
//             double marks=resultset.getDouble("marks");
//             System.out.println("Marks :"+ marks);
//         }else{
//             System.out.println("Marks not found");
//         }

//            preparedstatement.close();
//            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

