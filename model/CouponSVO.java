package javaShoppingMall.model;

public class CouponSVO {
    private String code;
    private String itemName;
    private int value;

    public CouponSVO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CouponSVO{" +
                "code='" + code + '\'' +
                ", itemName='" + itemName + '\'' +
                ", value=" + value +
                '}';
    }
}
