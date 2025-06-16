package javaShoppingMall.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CartDAOImp implements CartDAO{
    Connection conn;

    public CartDAOImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<CartSVO> selectUserCart(String userID) {
        List<CartSVO> allItems = new ArrayList<>();
        String selectSQL = "SELECT \"INDEX\", ITEM_NAME, ITEM_PRICE FROM CART INNER JOIN \"CATEGORY\" USING (ITEM_CODE) WHERE USER_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CartSVO item = new CartSVO();
                item.setIndex(rs.getInt("\"INDEX\""));
                item.setName(rs.getString("ITEM_NAME"));
                item.setPrice(rs.getInt("ITEM_PRICE"));
                allItems.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }

    @Override
    public void addItem(String product, UserVO user) {
        String insertSQL = "INSERT INTO CART VALUES (CART_SEQ.NEXTVAL,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, product);
            pstmt.setString(2, user.getId());

            int count = pstmt.executeUpdate();
            if (count == 0) {
                System.out.println("fail insert");
            } else {
                System.out.println("success insert " + count + "times");
            }
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("이미 장바구니에 있는 제품입니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllCart(String userID) {
        String insertSQL = "DELETE FROM CART WHERE USER_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, userID);

            int count = pstmt.executeUpdate();
            if (count == 0) {
                System.out.println("fail insert");
            } else {
                System.out.println("success delete " + count + "times");
            }
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("이미 장바구니에 있는 제품입니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSelecteItems(int[] deleteList) {
        String deleteIDX = Arrays.stream(deleteList).mapToObj(String::valueOf).collect(Collectors.joining(", "));
        String insertSQL = "DELETE FROM CART WHERE \"INDEX\" IN (" + deleteIDX +")";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            int count = pstmt.executeUpdate();
            if (count == 0) {
                System.out.println("fail insert");
            } else {
                System.out.println("success delete " + count + "times");
            }
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("이미 장바구니에 있는 제품입니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
