package javaShoppingMall.model;

import java.util.List;

public interface CartDAO {
    List<CartSVO> selectUserCart(String userID);

    void addItem(String s, UserVO user);

    void deleteAllCart(String userID);

    void deleteSelecteItems(int[] deleteList);

}
