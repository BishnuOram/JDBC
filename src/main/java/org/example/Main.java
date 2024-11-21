package org.example;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;

public class Main {
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

//            Statement statement =connection.createStatement();
//            String query="select * from student";
            String query = "delete from student where id=?";
            PreparedStatement preparedstatement = connection.prepareStatement(query);
            preparedstatement.setInt(1,106);
//            String query="select marks from student where marks";
//

//}
            int rowAffected = preparedstatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("data updated successfully");
            } else {
                System.out.println("data not inserted");
            }
//         if(resultset.next()){
//             double marks=resultset.getDouble("marks");
//             System.out.println("Marks :"+ marks);
//         }else{
//             System.out.println("Marks not found");
//         }
         connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
