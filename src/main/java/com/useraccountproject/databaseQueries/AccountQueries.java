package com.useraccountproject.databaseQueries;

import com.useraccountproject.connectionSetup.Configuration;
import com.useraccountproject.model.Account;

import java.sql.*;
import java.util.Scanner;

public class AccountQueries {
    //Method for creating an account
    public void addNewAccount() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Создание аккаунта для нового пользователя");
        System.out.println("Введите id вашего аккаунта:");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите номер телефона еще раз::");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Какую сумму хотите положить на счет?:");
        float balance = scanner.nextFloat();
        scanner.nextLine();

        System.out.println("В какой валюте будете хранить?:");
        String currency = scanner.nextLine();

        try {
            connection = Configuration.registerDriver();
            preparedStatement = connection.prepareStatement("INSERT INTO Accounts (accountId, userId, balance, currency) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, accountId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setFloat(3, balance);
            preparedStatement.setString(4, currency);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("Было создано %d аккаунтов", rows);
        } finally {
            Configuration.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    //Method to check if an account exists
    public boolean isAccountExists(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Accounts WHERE userId = " + userId;
            connection = Configuration.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            } else {
                System.out.println("У данного пользователя нет аккаунта");
                return false;
            }
        } finally {
            Configuration.closeConnection(connection, statement, resultSet);
        }
    }

    //Method for getting balance
    public int getBalance(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Accounts WHERE userId = " + userId;
            connection = Configuration.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            int balance = 0;
            while (resultSet.next()) {
                balance = resultSet.getInt(3);
                System.out.println("balance" + balance);
            }
            return balance;
        } finally {
            Configuration.closeConnection(connection, statement, resultSet);
        }
    }

    // Method for getting currency
    public String getCurrency(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Accounts WHERE userId = " + userId;
            connection = Configuration.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            String currency = null;
            while (resultSet.next()) {
                currency = resultSet.getString(4);
                System.out.println("currency" + currency);
            }
            return currency;
        } finally {
            Configuration.closeConnection(connection, statement, resultSet);
        }
    }

    //Method for replenishing the balance
    public void replanishTheBalance(float topUpAmount, int userId, String currency) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = new Account();
        try {
            String sql = "UPDATE Accounts SET balance = balance + ? WHERE userId = ? AND currency = ?";
            connection = Configuration.registerDriver();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, topUpAmount);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, currency);
            preparedStatement.executeUpdate();
            System.out.println("Счет пополнен");
        } finally {
            Configuration.closeConnection1(connection, preparedStatement);
        }
    }

    //Cash withdrawal method
    public void windrawCash(float topUpAmount, int userId, String currency) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = new Account();
        try {
            String sql = "UPDATE Accounts SET balance = balance - ? WHERE userId = ? AND currency = ?";
            connection = Configuration.registerDriver();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, topUpAmount);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, currency);
            preparedStatement.executeUpdate();
            System.out.println("Вы сняли наличные");
        } finally {
            Configuration.closeConnection1(connection, preparedStatement);
        }
    }
}
