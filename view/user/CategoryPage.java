package javaShoppingMall.view.user;

import javaShoppingMall.model.CategoryVO;
import javaShoppingMall.model.Database;
import javaShoppingMall.model.UserVO;

import java.util.List;
import java.util.Scanner;

public class CategoryPage {
    public void categoryPage(Database db, UserVO user) {
        List<CategoryVO> categoryList = db.categoryDAO.selectAllItem();
        Scanner s = new Scanner(System.in);
        System.out.println("판매 상품 목록");
        for (CategoryVO i : categoryList) {
            System.out.println(i);
        }
        boolean stopflag = false;
        while (!stopflag) {
            System.out.println("1.정렬 2.장바구니에 추가 3.메뉴로 돌아가기");
            int choice = Integer.parseInt(s.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("정렬방식 선택");
                    System.out.println("1.가격싼순서 2.가격비싼순서");
                    int sortChoice = Integer.parseInt(s.nextLine());
                    categoryList = db.categoryDAO.sortingItem(sortChoice);
                    for (CategoryVO i : categoryList) {
                        System.out.println(i);
                    }
                    break;
                case 2:
                    System.out.print("장바구니에 추가할 제품의 코드를 입력해주세요: ");
                    String productCode = s.nextLine();
                    db.cartDAO.addItem(productCode, user);
                    break;
                default:
                    stopflag = true;
            }
        }
    }
}
