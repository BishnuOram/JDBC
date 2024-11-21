package org.example.anp;

import java.sql.*;


public class SimpleJDBCclass {
    private static final String url = "jdbc:mysql://localhost:3306/anudip";
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
                String StudentID = resultset.getString("StudentID");
                String FirstName = resultset.getString("FirstName");
                String LastName = resultset.getString("LastName");
                java.sql.Date DateOfBirth = resultset.getDate("DateOfBirth");
                String Gender = resultset.getString("Gender");
                String Email = resultset.getString("Email");
                Long Phone=resultset.getLong("Phone");

                System.out.println("StudentID is :" + StudentID);
                System.out.println("FirstName is :" + FirstName);
                System.out.println("LastName is :" + LastName);
                System.out.println("Gender is :" + Gender);
                System.out.println("Email is :" + Email);
                System.out.println("Phone no is :"+Phone);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

//Output :
//StudentID is :S101
//FirstName is :John
//LastName is :Doe
//Gender is :Male
//Email is :john@gmail.com
//Phone no is :9439827472
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//StudentID is :S102
//FirstName is :Jane
//LastName is :Smith
//Gender is :Male
//Email is :jane@gmail.com
//Phone no is :9446748908
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//StudentID is :S103
//FirstName is :Alice
//LastName is :Johnson
//Gender is :Female
//Email is :alice@gmail.com
//Phone no is :7324579078
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//StudentID is :S104
//FirstName is :Anna
//LastName is :Doe
//Gender is :Female
//Email is :Anna@gmail.com
//Phone no is :9826881457
//++++++++++++++++++++++++++++++++++++++++++++++++++++++
//StudentID is :S105
//FirstName is :Harry
//LastName is :Lane
//Gender is :Male
//Email is :harry@.com
//Phone no is :9655263579
//++++++++++++++++++++++++++++++++++++++++++++++++++++++