package javaShoppingMall.model;

public class CartVO {
    int idx;
    String itemCode;
    String userID;

    public CartVO() {
    }
    public CartVO(String itemCode, String userID) {
        this.itemCode = itemCode;
        this.userID = userID;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "idx=" + idx +
                ", itemCode='" + itemCode + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
