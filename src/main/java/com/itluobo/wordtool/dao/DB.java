package com.itluobo.wordtool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kenvi on 16/2/16.
 */
public class DB {
    private static String user = "test";
    private static String password = "test";

    public static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            }catch (Throwable t) {
                t.printStackTrace();
            }
        }

    }
}
