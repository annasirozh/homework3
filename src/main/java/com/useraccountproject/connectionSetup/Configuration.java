package com.useraccountproject.connectionSetup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Configuration {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE_URL=
            "jdbc:sqlite:C:\\Users\\psiro\\IdeaProjects\\UserAccountProject\\mydb.db";

    public static boolean isJDBCDriverExists() {
        try {
            Class.forName(JDBC_DRIVER);
            return true;
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage() + "JDBC driver not found");
            return false;
        }
    }

    public static Connection connectWithDB(){
        if(isJDBCDriverExists()) {
          try {
              return java.sql.DriverManager.getConnection(DATABASE_URL);
          } catch (SQLException throwables) {
              System.out.println("No suitable driver found for URL");
          }
        }
        return null;
    }

    public static Connection registerDriver (){
        return Configuration.connectWithDB();
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        try {
            statement.close();
            connection.close();
        } finally {
            if (statement !=null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
    public static void closeConnection1(Connection connection, Statement statement) throws SQLException {
        try {
            statement.close();
            connection.close();
        } finally {
            if (statement !=null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

}
