package com.useraccountproject.databaseQueries;

import com.useraccountproject.connectionSetup.Configuration;
import com.useraccountproject.model.User;

import java.sql.*;
import java.util.Scanner;


public class UsersQueries {

    //Method for select all users from table Users
    public void selectAllUsers() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Configuration.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT *FROM Users");
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setAddress(resultSet.getString("address"));
                System.out.println("UserId" + user.getUserId());
                System.out.println("name" + user.getName());
                System.out.println("address" + user.getAddress());
            }
        } finally {
            Configuration.closeConnection(connection, statement, resultSet);
        }
    }

    //Method for registration new user
    public void registerNewUser() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер телефона:");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите свое имя:");
        String name = scanner.nextLine();

        System.out.println("Введите свой адрес:");
        String address = scanner.nextLine();

        try {
            connection = Configuration.registerDriver();
            preparedStatement = connection.prepareStatement("INSERT INTO Users(userId, name, address) VALUES(?,?,?)");
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);

            int rows = preparedStatement.executeUpdate();
            System.out.printf("В таблицу добавлено %d записей", rows);
        } finally {
            Configuration.closeConnection(connection, preparedStatement, resultSet);
        }
    }


    ///Method to check if user exists
    public boolean isUsersExists(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM Users WHERE userId = " + userId;
            connection = Configuration.registerDriver();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            } else {
                System.out.println("Пользователя с таким userId не существует");
                return false;
            }
        } finally {
            Configuration.closeConnection(connection, statement, resultSet);
        }
    }
}
