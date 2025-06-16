package javaShoppingMall.model;


public class CategoryVO{
	private String code;

	private String name;

	private char category;
	private char clotheGender;
	private String size;
	private int price;
	public CategoryVO() {
		super();
	}
	public CategoryVO(String name, char category, char clotheGender, String size, int price) {
		super();
		this.name = name;
		this.category = category;
		this.clotheGender = clotheGender;
		this.size = size;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getCategory() {
		return category;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public char getClotheGender() {
		return clotheGender;
	}
	public void setClotheGender(char clotheGender) {
		this.clotheGender = clotheGender;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Shopping [code="+ code +", name=" + name + ", category=" + category + ", clotheGender=" + clotheGender + ", size=" + size
				+ ", price=" + price + "]";
	}

}
