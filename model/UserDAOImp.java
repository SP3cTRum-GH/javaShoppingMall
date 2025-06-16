package javaShoppingMall.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO{
    Connection conn;

    public UserDAOImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<UserVO> selectAllUsers() {
        List<UserVO> allUsers = new ArrayList<>();
        String selectSQL = "SELECT * FROM USER_INFO";
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                UserVO user = new UserVO();
                user.setId(rs.getString("USER_ID"));
                user.setPwd(rs.getString("PWD"));
                user.setName(rs.getString("USER_NAME"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    @Override
    public UserVO searchUser(UserVO user) {
        String selectSQL = "SELECT * FROM USER_INFO WHERE USER_ID = ?";
        UserVO loginUser = new UserVO();
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1,user.getId());

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                loginUser.setId(rs.getString("USER_ID"));
                loginUser.setPwd(rs.getString("PWD"));
                loginUser.setName(rs.getString("USER_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loginUser;
    }

    @Override
    public void addUser(UserVO addUser) {
        String insertSQL = "INSERT INTO USER_INFO VALUES (?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, addUser.getId());
            pstmt.setString(2, addUser.getPwd());
            pstmt.setString(3, addUser.getName());

            int count = pstmt.executeUpdate();
            if (count == 0) {
                System.out.println("fail insert");
            } else {
                System.out.println("success insert " + count + "times");
            }
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("이미 가입된 아이디 입니다. 다른 아이디를 사용해 주세요");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
