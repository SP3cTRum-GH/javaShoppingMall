package javaShoppingMall.model;

public class CartSVO {
    private int index;
    private String name;
    private int price;

    public CartSVO() {
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UserCart [name=" + name + ", price=" + price + "]";
    }

}
