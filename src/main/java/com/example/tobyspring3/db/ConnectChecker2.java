package com.example.tobyspring3.db;

import java.sql.*;

public class ConnectChecker2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-39-247-183.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "12345678");

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW DATABASES");

        while (rs.next()) {
            String line = rs.getString(1);
            System.out.println(line);
        }

        ConnectChecker2 cc = new ConnectChecker2();
        cc.add();
    }
    public void add() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://ec2-3-39-247-183.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "12345678");

        // PrepatedStatement : 쿼리를 반복적으로 실행하거나 사용자 입력과 같은 변수 데이터를 넣을 때
        // ? : placeholder : 나중에 setString 메소드를 통해서 실제 값을 정함
        // setStatement는 완성된 쿼리를 넣어줘야한다.
        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "1");
        pstmt.setString(2, "kyeongrok");
        pstmt.setString(3, "12345678");
        pstmt.executeUpdate();
    }
}