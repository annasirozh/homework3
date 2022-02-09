package com.useraccountproject.databaseQueries;

import com.useraccountproject.connectionSetup.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionQueries {
    //Method for adding transaction data
    public void addTransction(float topUpamount) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите id транзакции:");
        int transactinId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ввеите id вашего аккаунта:");
        int accountId = scanner.nextInt();

        try {
            connection = Configuration.registerDriver();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Transactions (transactinId, accountId,amount) VALUES(?,?,?)");
            preparedStatement.setInt(1, transactinId);
            preparedStatement.setInt(2, accountId);
            preparedStatement.setFloat(3, topUpamount);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("В таблицу добавлено %d записей", rows);
        } finally {
            Configuration.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
