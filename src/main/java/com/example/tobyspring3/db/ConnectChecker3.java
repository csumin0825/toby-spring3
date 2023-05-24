package com.example.tobyspring3.db;

import java.sql.*;

public class ConnectChecker3 {

    public void check() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-39-247-183.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "12345678");

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW DATABASES");
        while (rs.next()) {
            String line = rs.getString(1);
            System.out.println(line);
        }
    }

    public void select() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-39-247-183.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "12345678");

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        while (rs.next()) {
            String col1 = rs.getString(1);
            String col2 = rs.getString(2);
            String col3 = rs.getString(3);
            System.out.printf("%s %s %s\n", col1, col2, col3);
        }

    }

    public void add() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-39-247-183.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "12345678");
//        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
//        pstmt.setString(1, "1");
//        pstmt.setString(2, "kyeongrok");
//        pstmt.setString(3, "12345678");

        // id 자동 증가 됨
        PreparedStatement pstmt = conn.prepareStatement("insert into users(name, password) values(?, ?)");
        pstmt.setString(1,"choichoi");
        pstmt.setString(2,"123456");

        pstmt.executeUpdate();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectChecker3 cc = new ConnectChecker3();
        cc.add();
        cc.select();
    }
}