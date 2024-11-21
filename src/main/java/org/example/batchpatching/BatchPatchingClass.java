package org.example.batchpatching;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchPatchingClass {
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
            String query = "insert into student(name,age,marks) values(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner scanner=new Scanner(System.in);

            while(true){
                System.out.println("Enter name :");
                String name=scanner.nextLine();
                System.out.println("Enter age :");
                int age=scanner.nextInt();
                System.out.println("Enter marks :");
                double marks=scanner.nextDouble();
                System.out.println("Enter choice (Y/N)");
                String choice=scanner.next();
                preparedStatement.setString(   1, name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,35.5);
                preparedStatement.addBatch();
                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }

        int arr[]= preparedStatement.executeBatch();

            for(int i=0;i<arr.length;i++){
                if(arr[i]==0) {
                    System.out.println("Query" + i + " not execute successfully");
          }      }
//                    if (arr.length > 0) {
//                        System.out.println("All inserted..");
//                    } else {
//                        System.out.println("Not inserted..");
//                    }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
