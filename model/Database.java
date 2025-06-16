package javaShoppingMall.model;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private Connection conn;
    public UserDAO userDAO;
    public CouponDAO couponDAO;
    public CategoryDAO categoryDAO;
    public CartDAO cartDAO;
    public PaylogDAO paylogDAO;
    public Database() {
        try {
            this.conn = DBUtil.getConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.userDAO = new UserDAOImp(conn);
        this.couponDAO = new CouponDAOImp(conn);
        this.categoryDAO = new CategoryDAOImp(conn);
        this.cartDAO = new CartDAOImp(conn);
        this.paylogDAO = new PaylogDAOImp(conn);
    }
}
