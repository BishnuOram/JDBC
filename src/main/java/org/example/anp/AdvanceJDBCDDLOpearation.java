package org.example.anp;

import java.sql.*;
import java.util.Scanner;

public class AdvanceJDBCDDLOpearation {
    private static final String url = "jdbc:mysql://localhost:3306/anudip";
    private static final String username = "root";
    private static final String password = "root";
    Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
//            String query = "select * from student";


//            int choice=scanner.next()
            createTable(connection);

            // Alter the table structure (add columns, modify data types, etc.)
            alterTable(connection);

            // Insert data into the table
            insertData(connection, 1, "Johnson Bada", "john@gmail.com");
            insertData(connection, 2, "Abraham linker", "abraham@gmail.com");

            // Update existing records
            updateData(connection, 2, "Abraham linker", "linker@gmail.com");

            // Delete records based on specific conditions
            deleteData(connection, 1);

            // Close the connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Employee (" +
                "EmpId INT PRIMARY KEY," +
                "EmpName VARCHAR(255) NOT NULL," +
                "EmpEmail VARCHAR(255) UNIQUE NOT NULL" +
                ")";
        try (Statement statement = connection.createStatement()) {
     statement.executeUpdate(createTableSQL);

        }
    }

    private static void alterTable(Connection connection) throws SQLException {
        // Add a new column to the table
        String alterTableSQL = "ALTER TABLE Employee ADD COLUMN EmpAge INT";
        try (Statement statement = connection.createStatement()) {
       statement.executeUpdate(alterTableSQL);
        }
    }

    private static void insertData(Connection connection, int EmpId, String EmpName, String EmpEmail) throws SQLException {
        String insertDataSQL = "INSERT INTO Employee (EmpId, EmpName, EmpEmail) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
//            while(preparedStatement.execute()) {
                preparedStatement.setInt(1, EmpId);
                preparedStatement.setString(2, EmpName);
                preparedStatement.setString(3, EmpEmail);
            int rowAffected1=preparedStatement.executeUpdate();
            if(rowAffected1>0){
                System.out.println("Insert data successfully");
            }else{
                System.out.println("Not inserted..");
            }
        }
    }

    private static void updateData(Connection connection, int EmpId, String EmpName, String EmpEmail) throws SQLException {
        String updateDataSQL = "UPDATE Employee SET EmpEmail = ? WHERE EmpId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateDataSQL)) {
            preparedStatement.setString(1, EmpEmail);
            preparedStatement.setInt(2, EmpId);
            int rowAffected2=preparedStatement.executeUpdate();
            if(rowAffected2>0){
                System.out.println("Table updated successfully.");
            }else{
                System.out.println("Table not update successfully.");
            }
        }
    }

    private static void deleteData(Connection connection, int EmpId) throws SQLException {
        String deleteDataSQL = "DELETE FROM Employee WHERE EmpId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setInt(1, EmpId);
            int rowAffected3=preparedStatement.executeUpdate();
        if(rowAffected3>0){
            System.out.println("Data deleted successfully.");
        }else{
            System.out.println("Data not deleted successfully.");
        }
        }
    }
}

//Output :
//Insert data successfully
//Insert data successfully
//Table updated successfully.
//Data deleted successfully.