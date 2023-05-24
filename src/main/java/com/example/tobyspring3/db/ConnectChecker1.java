package com.example.tobyspring3.db;

import java.sql.*;

public class ConnectChecker1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // MySQL 드라이버를 로드
        Class.forName("com.mysql.cj.jdbc.Driver");
        // DB에 연결하겠다는 뜻 (Connection을 만들겠다는 것)
        Connection conn = DriverManager.getConnection("jdbc:mysql:// ec2-3-39-247-183.ap-northeast-2.compute.amazonaws.com/spring-db",
                "root", "12345678");

        // Connection 객체 conn을 이용해서 Statement객체 stmt 만들기
        Statement stmt = conn.createStatement();

        // stmt 객체를 사용하여 SHOW DATABASES 라는 SQL 쿼리를 실행하고 그 결과를 rs에 저장한다
        // ** ResultSet은 select문에서 사용한다.
        ResultSet rs = stmt.executeQuery("SHOW DATABASES");
//        rs = st.getResultSet();

        // null이 나올때까지 - 결과가 없을 때까지 - 결과를 다 출력할 때까지
        while (rs.next()) {
            String line = rs.getString(1);
            System.out.println(line);
        }
    }
}


// 교안 코드
//public class ConnectChecker {
//    public void check() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/toby-spring3",
//                "root", "12345678");
//
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery("SHOW DATABASES");
//        rs = st.getResultSet();
//        while (rs.next()) {
//            String str = rs.getString(1);
//            System.out.println(str);
//        }
//    }
//
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ConnectChecker cc = new ConnectChecker();
//        cc.check();
//    }
//}