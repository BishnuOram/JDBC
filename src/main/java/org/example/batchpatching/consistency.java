package org.example.batchpatching;

import java.sql.*;
import java.util.Scanner;

public class consistency {
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
            connection.setAutoCommit(false);

            String debit_query = "UPDATE account SET balance=balance- ? WHERE account_number=?";
            String credit_query = "UPDATE account SET balance =balance+ ? WHERE account_number=?";

            PreparedStatement debitpreparedStatement = connection.prepareStatement(debit_query);
            PreparedStatement creditpreparedStatement = connection.prepareStatement(credit_query);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Account Number :");
            int account_number = scanner.nextInt();
            System.out.println("Enter amount :");
            int amount = scanner.nextInt();


            debitpreparedStatement.setInt(1, amount);
            debitpreparedStatement.setInt(2, account_number);
            creditpreparedStatement.setInt(1, amount);
            creditpreparedStatement.setInt(2, 102);

            debitpreparedStatement.executeUpdate();
            creditpreparedStatement.executeUpdate();

            if (isSufficient(connection, account_number, amount)) {
                connection.commit();
                System.out.println("Transaction successful");
            } else {
                connection.rollback();
                System.out.println("Transaction failed");
            }
            debitpreparedStatement.executeQuery();
            creditpreparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isSufficient(Connection connection, int account_number, int amount) {
        try {
            String query = "SELECT balance FROM account WHERE account_number=?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setInt(1, account_number);
            ResultSet resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                double current_balance = resultSet.getDouble("balance");
                if (amount > current_balance) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
