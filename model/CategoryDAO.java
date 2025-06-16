package javaShoppingMall.model;

import java.util.List;

public interface CategoryDAO {
    List<CategoryVO> selectAllItem();
    List<CategoryVO> selectItem(String productName);
    void addProduct(CategoryVO product);

    void deleteProduct(String itemCode);

    List<CategoryVO> sortingItem(int option);
}
