package javaShoppingMall.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImp implements CategoryDAO{
    Connection conn;

    public CategoryDAOImp(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void addProduct(CategoryVO product) {
        String insertSQL = "INSERT INTO \"CATEGORY\" VALUES (?||?||LPAD(CATEGORY_SEQ.NEXTVAL, 6, '0'),?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, String.valueOf(product.getCategory()));
            pstmt.setString(2, String.valueOf(product.getClotheGender()));
            pstmt.setString(3, product.getName());
            pstmt.setInt(4, product.getPrice());
            pstmt.setString(5,String.valueOf(product.getClotheGender()));
            pstmt.setString(6,product.getSize());
            pstmt.setString(7,String.valueOf(product.getCategory()));

            int count = pstmt.executeUpdate();
            if (count == 0) {
                System.out.println("fail insert");
            } else {
                System.out.println("success insert " + count + "times");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(String itemCode) {
        String deleteSQL = "DELETE FROM \"CATEGORY\" WHERE ITEM_CODE = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setString(1, itemCode);

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

    //    ITEM_CODE VARCHAR2(8),
//    ITEM_NAME VARCHAR2(20) NOT NULL,
//    ITEM_PRICE NUMBER NOT NULL,
//    CLOTHE_GENDER CHAR NOT NULL,
//    ITEM_SIZE VARCHAR2(3) NOT NULL,
//    ITEM_CATEGORY CHAR NOT NULL,
    @Override
    public List<CategoryVO> selectAllItem() {
        List<CategoryVO> allItems = new ArrayList<>();
        String selectSQL = "SELECT * FROM \"CATEGORY\"";
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CategoryVO item = new CategoryVO();
                item.setCode(rs.getString("ITEM_CODE"));
                item.setName(rs.getString("ITEM_NAME"));
                item.setPrice(rs.getInt("ITEM_PRICE"));
                item.setClotheGender(rs.getString("CLOTHE_GENDER").charAt(0));
                item.setSize(rs.getString("ITEM_SIZE"));
                item.setCategory(rs.getString("ITEM_CATEGORY").charAt(0));
                allItems.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }

    @Override
    public List<CategoryVO> selectItem(String productName) {
        List<CategoryVO> searchItem = new ArrayList<>();
        String selectSQL = "SELECT * FROM \"CATEGORY\" WHERE ITEM_NAME = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CategoryVO item = new CategoryVO();
                item.setCode(rs.getString("ITEM_CODE"));
                item.setName(rs.getString("ITEM_NAME"));
                item.setPrice(rs.getInt("ITEM_PRICE"));
                item.setClotheGender(rs.getString("CLOTHE_GENDER").charAt(0));
                item.setSize(rs.getString("ITEM_SIZE"));
                item.setCategory(rs.getString("ITEM_CATEGORY").charAt(0));
                searchItem.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchItem;
    }

    @Override
    public List<CategoryVO> sortingItem(int option) {
        String sortOption;
        if(option == 1){
            sortOption = "ASC";
        }else {
            sortOption = "DESC";
        }
        List<CategoryVO> allItems = new ArrayList<>();
        String selectSQL = "SELECT * FROM \"CATEGORY\" ORDER BY ITEM_PRICE " + sortOption;
        try {
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CategoryVO item = new CategoryVO();
                item.setCode(rs.getString("ITEM_CODE"));
                item.setName(rs.getString("ITEM_NAME"));
                item.setPrice(rs.getInt("ITEM_PRICE"));
                item.setClotheGender(rs.getString("CLOTHE_GENDER").charAt(0));
                item.setSize(rs.getString("ITEM_SIZE"));
                item.setCategory(rs.getString("ITEM_CATEGORY").charAt(0));
                allItems.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }
}
