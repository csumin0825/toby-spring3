package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;

public class UserDao {
    ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // DB 연결
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMaker.makeConnection();

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
}
