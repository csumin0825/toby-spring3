package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public abstract class UserDao {
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
//        Map<String, String> env = getenv();
//        String dbHost = env.get("DB_HOST");
//        String dbUser = env.get("DB_USER");
//        String dbPassword = env.get("DB_PASSWORD");
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection conn = DriverManager.getConnection(dbHost, dbUser,dbPassword);
//        return conn;
//    }

//    public void add(User user) throws ClassNotFoundException, SQLException {
//        Connection conn = getConnection();
//        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?,?,?)");
//        pstmt.setString(1,user.getId());
//        pstmt.setString(2,user.getName());
//        pstmt.setString(3,user.getPassword());
//
//        pstmt.executeUpdate();
//
//        pstmt.close();
//        conn.close();
//    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        // DB 연결
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        PreparedStatement pstmt = conn.prepareStatement("select id,name,password from users where id =?");
        pstmt.setString(1, id);
        // executeQuery를 쓰는 이유 : select문은 결과값이 있기 때문에
        ResultSet  rs = pstmt.executeQuery();
        rs.next(); // MySQL 콘솔에서 ctrl+enter 누르는 것과 비슷함

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // 안닫으면 db와 계속 연결되어있음
        // 계속 실행되어있으면 접속이 계속 늘어난다. (커넥션 풀이 꽉 차지않도록)
        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new NUserDao();
        User user = new User();
        user.setId("4");
        user.setName("최미미");
        user.setPassword("123456");
        userDao.add(user);

//        User selectedUser = userDao.get("1");
//        System.out.println(selectedUser.getId());
//        System.out.println(selectedUser.getName());
//        System.out.println(selectedUser.getPassword());
    }
}
