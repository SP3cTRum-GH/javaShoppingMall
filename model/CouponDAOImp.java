package javaShoppingMall.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponDAOImp implements CouponDAO{
    Connection conn;

    public CouponDAOImp(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<CouponVO> selectUserCoupon(String userName) {
        List<CouponVO> userCouponList = new ArrayList<>();
        String selectSQL = "SELECT * FROM COUPON WHERE USER_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CouponVO item = new CouponVO();
                item.setCode(rs.getString("CODE"));
                item.setUserID(rs.getString("USER_ID"));
                item.setItemCode(rs.getString("ITEM_CODE"));
                item.setValue(rs.getInt("\"VALUE\""));

                userCouponList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userCouponList;
    }

    @Override
    public void createCoupon(CouponVO coupon) {
        String insertSQL = "INSERT INTO COUPON VALUES ('" + coupon.getItemCode().substring(0,2)+"'||'"+coupon.getUserID().charAt(0)+ "'||LPAD(COUPON_SEQ.NEXTVAL, 5, '0'),?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setInt(1, coupon.getValue());
            pstmt.setString(2, coupon.getUserID());
            pstmt.setString(3,coupon.getItemCode());

            int count = pstmt.executeUpdate();
            if (count == 0) {
                System.out.println("fail insert");
            } else {
                System.out.println("success insert " + count + "times");
            }
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("이미 발급된 쿠폰입니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CouponSVO> selectUseableCoupon(String userName) {
        List<CouponSVO> userCouponList = new ArrayList<>();
        String selectSQL = "SELECT COUPON.CODE,\"CATEGORY\".ITEM_NAME, COUPON.\"VALUE\" FROM CART "+
        "INNER JOIN COUPON ON CART.ITEM_CODE = COUPON.ITEM_CODE "+
        "INNER JOIN \"CATEGORY\" ON CART.ITEM_CODE = CATEGORY.ITEM_CODE WHERE COUPON.USER_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CouponSVO item = new CouponSVO();
                item.setCode(rs.getString("CODE"));
                item.setItemName(rs.getString("ITEM_NAME"));
                item.setValue(rs.getInt("\"VALUE\""));

                userCouponList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userCouponList;
    }

    @Override
    public void deleteCoupon(String useCoupon) {
        String deleteSQL = "DELETE FROM COUPON WHERE CODE = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setString(1, useCoupon);

            int count = pstmt.executeUpdate();
            if(count == 0){
                System.out.println("fail delete");
            }else {
                System.out.println("success delete " + count+"times");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
