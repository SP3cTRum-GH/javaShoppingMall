package javaShoppingMall.model;

import java.sql.Date;

public class PaylogVO {
    private  int idx;
    private String userID;
    private String payItem;
    private  int payPrice;
    private Date payDate;

    public PaylogVO() {
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public int getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(int payPrice) {
        this.payPrice = payPrice;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "PaylogVO{" +
                ", userID='" + userID + '\'' +
                ", payItem='" + payItem + '\'' +
                ", payPrice=" + payPrice +
                ", payDate=" + payDate +
                '}';
    }
}
