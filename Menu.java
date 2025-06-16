package javaShoppingMall;

public interface Menu {
	//사용자 메뉴 상수
	public static final int USER_INFO_VIEW = 1;
	public static final int CART_PURCHASE_LIST = 2;
	public static final int PRODUCT_LIST_VIEW = 3;
	public static final int CART_LIST_CLEAR = 4;
	public static final int CART_ITEM_ASCENDING = 5;
	public static final int CART_ITEM_DESCENDING = 6;
	public static final int CART_ITEM_ADD = 7;
	public static final int CART_ITEM_DELETE = 8;
	public static final int DISCOUNT_COUPON_APPLY = 9;
	public static final int BILL_OUTPUT= 10;
	public static final int CART_PAYMENT= 11;
	public static final int EXIT = 12;
	
	//관리자 메뉴 상수
	public static final int ADMIN_USER_INFO_VIEW = 1;
    public static final int ADMIN_ADD_PRODUCT = 2;
    public static final int ADMIN_REMOVE_PRODUCT = 3;
    public static final int ADMIN_EXIT = 5;
	
    //사용자 메뉴 메소드
	public static void userMenuDisplay() {
//		System.out.println("================== menu ====================");
//		System.out.println("1. 고객 정보 확인하기    	2. 고객 구매품목 목록 출력");
//		System.out.println("3. 상품 목록 출력  		4. 구매품목 초기화 ");
//		System.out.println("5. 고객 구매품록 오름차순  	6. 고객 구매품록 내림차순");
//		System.out.println("7. 구매품목 항목 추가    	8. 구매품목 항목 삭제");
//		System.out.println("9. 할인쿠폰 적용    		10. 영수증 출력");
//		System.out.println("11. 장바구니 결제    		12. 프로그램 종료");
//		System.out.println("============================================");

		System.out.println("======== menu ==========");
		System.out.println("\t1.마이페이지");
		System.out.println("\t2.상품페이지");
		System.out.println("\t3.장바구니");
		System.out.println("\t4.로그아웃");
		System.out.println("========================");
	}
	//관리자 메뉴 메소드
	public static void adminMenuDisplay() {
		System.out.println("====== menu ======");
		System.out.println("1.사용자 정보 확인");
		System.out.println("2.판매 상품 추가");
		System.out.println("3.판매 상품 삭제");
		System.out.println("4.쿠폰 발급");
		System.out.println("5.로그아웃");
		System.out.println("==================");
	}

	public static void memberManagerMenu() {
		System.out.println();
		System.out.println("===============");
		System.out.println("1. 로그인");
		System.out.println("2. 회원 가입");
		System.out.println("3. 프로그램 종료");
		System.out.println("===============");
		System.out.print("번호 선택 : ");
	}
}
