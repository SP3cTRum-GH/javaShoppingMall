package javaShoppingMall.view.user;

import javaShoppingMall.Menu;
import javaShoppingMall.model.Database;
import javaShoppingMall.model.UserVO;

import java.util.Scanner;

//================== menu ====================
//        1. 고객 정보 확인하기    	2. 고객 구매품목 목록 출력
//3. 상품 목록 출력  		4. 구매품목 초기화
//5. 고객 구매품록 오름차순  	6. 고객 구매품록 내림차순
//7. 구매품목 항목 추가    	8. 구매품목 항목 삭제
//9. 할인쿠폰 적용    		10. 영수증 출력
//11. 장바구니 결제    		12. 프로그램 종료
//============================================

public class UserMenu {
    Database db;
    UserVO user;

    public UserMenu(Database db, UserVO user) {
        this.db = db;
        this.user = user;
    }

    public void userMenu(){
        Scanner s = new Scanner(System.in);
        Menu.userMenuDisplay();
        System.out.print("메뉴선택: ");
        int choice = Integer.parseInt(s.nextLine());
        switch(choice){
            case 1: //마이페이지 -고객정보 - 결제정
                new MyPage().myPage(user, db);
                break;
            case 2: //상품페이지 - 필터적용, 상품구매(장바구니에 추가)
                new CategoryPage().categoryPage(db, user);
                break;
            case 3: //장바구니페이지 - 필터적용 , 장바구니 초기화, 구매 - 할인쿠폰 적용, 삭제
                new CartPage().cartPage(db, user);
                break;
            default:
                return;
        }
        userMenu();
    }
}
