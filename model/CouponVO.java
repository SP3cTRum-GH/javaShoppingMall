package javaShoppingMall.model;

public class CouponVO {
	private String code;
	private int value;

	private String itemCode;
	private String userID;

	public CouponVO() {
	}

	public CouponVO(String code, int value, String itemCode, String userID) {
		this.code = code;
		this.value = value;
		this.itemCode = itemCode;
		this.userID = userID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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
		return "CouponVO{" +
				"code='" + code + '\'' +
				", value=" + value +
				", itemCode='" + itemCode + '\'' +
				", userID='" + userID + '\'' +
				'}';
	}
}
