package com.tech.blog.helpers;

import java.sql.*;

public class ConnectionProvider {

    private static String url, username, password;

    static {
        url = "jdbc:mysql://localhost:3306/tech_blogs";
        username = "root";
        password = "charlie";
    }
    private static Connection con;

    public static Connection getConnection() {

        try {
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
