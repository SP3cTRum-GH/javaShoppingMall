package javaShoppingMall.model;

import java.util.List;

public interface PaylogDAO {

    List<PaylogVO> selectUserPay(String userID);
    void pay(String userID, List<CartSVO>cartList);
}
