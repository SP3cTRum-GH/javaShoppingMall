package javaShoppingMall.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaylogDAOImp implements  PaylogDAO{
    Connection conn;

    public PaylogDAOImp(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void pay(String userID, List<CartSVO>cartList) {
        String insertSQL = "INSERT INTO PAYLOG VALUES (PAYLOG_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            for (CartSVO i : cartList) {
                pstmt.setString(1, userID);
                pstmt.setString(2, i.getName());
                pstmt.setInt(3, i.getPrice());
                pstmt.addBatch();  // 배치에 추가
            }

            int[] count = pstmt.executeBatch();
            System.out.println("총 " + count.length + "개의 결제내역이 추가되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private  int idx;
//    private String userID;
//    private String payItem;
//    private  int payPrice;
//    private Date payDate;

    @Override
    public List<PaylogVO> selectUserPay(String userID) {
        List<PaylogVO> allItems = new ArrayList<>();
        String selectSQL = "SELECT * FROM PAYLOG WHERE USER_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PaylogVO item = new PaylogVO();
                item.setIdx(rs.getInt("IDX"));
                item.setUserID(rs.getString("USER_ID"));
                item.setPayItem(rs.getString("PAY_ITEM"));
                item.setPayPrice(rs.getInt("PAY_PRICE"));
                item.setPayDate(rs.getDate("PAY_DATE"));
                allItems.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }
}
