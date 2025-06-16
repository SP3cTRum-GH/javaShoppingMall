package javaShoppingMall.view.admin;

import javaShoppingMall.Menu;
import javaShoppingMall.model.CategoryVO;
import javaShoppingMall.model.CouponVO;
import javaShoppingMall.model.Database;
import javaShoppingMall.model.UserVO;

import java.util.List;
import java.util.Scanner;

public class AdminPage {
    Database db;

    public AdminPage(Database db) {
        this.db = db;
    }

    public void adminPage() {
        Scanner s = new Scanner(System.in);
        boolean stopFlag = false;
        while (!stopFlag) {
            System.out.println();
            Menu.adminMenuDisplay();
            int no = Integer.parseInt(s.nextLine());
            switch (no) {
                case Menu.ADMIN_USER_INFO_VIEW:
                    List<UserVO> allUsers = db.userDAO.selectAllUsers();
                    for(UserVO i : allUsers){
                        System.out.println(i);
                    }
                    break;
                case Menu.ADMIN_ADD_PRODUCT:
                    System.out.print("추가하실 옷을 이름을 입력하세요: ");
                    String name = s.nextLine();
                    System.out.print("추가하실 옷의 분류를 입력해주세요: ");
                    char category = s.nextLine().charAt(0);
                    System.out.print("추가하실 옷의 착용성별(M,W,F)을 입력해주세요: ");
                    char clothegender = s.nextLine().charAt(0);
                    System.out.print("추가하실 옷의 사이즈(S,M,L)를 입력해주세요: ");
                    String size = s.nextLine();
                    System.out.print("추가하실 옷의 가격을 입력해주세요: ");
                    int price = Integer.parseInt(s.nextLine());
                    CategoryVO shopping = new CategoryVO(name, category, clothegender, size, price);

                    db.categoryDAO.addProduct(shopping);
                    break;
                case Menu.ADMIN_REMOVE_PRODUCT:
                    boolean removeFlag = false;
                    System.out.print("삭제할 제품의 이름을 입력>");
                    String productName = s.nextLine();
                    List<CategoryVO>deleteItem = db.categoryDAO.selectItem(productName);
                    if(deleteItem.isEmpty()){
                        System.out.println("제품을 찾을 수 없습니다.");
                        break;
                    }
                    else if(deleteItem.size() == 1){
                        db.categoryDAO.deleteProduct(deleteItem.get(0).getCode());
                    }else {
                        for(CategoryVO i : deleteItem){
                            System.out.println(i);
                        }
                        System.out.println("몇번째 제품을 삭제할까요?: ");
                        int choice = Integer.parseInt(s.nextLine());
                        db.categoryDAO.deleteProduct(deleteItem.get(choice-1).getCode());
                    }
                    System.out.println("삭제완료");

                    break;
                case 4:
                    List<CategoryVO>allCategorys = db.categoryDAO.selectAllItem();
                    List<UserVO> allUser = db.userDAO.selectAllUsers();
                    CouponVO coupon = new CouponVO();
                    for(CategoryVO i : allCategorys){
                        System.out.println(i);
                    }
                    System.out.println("어떤제품의 쿠폰을 발급하시겠습니까?");
                    coupon.setItemCode(s.nextLine());
                    System.out.println("몇퍼센트 할인쿠폰을 발급하시겠습니까?");
                    coupon.setValue(Integer.parseInt(s.nextLine()));
                    for(UserVO i : allUser){
                        System.out.println(i);
                    }
                    System.out.println("어떤 유저에게 발급하시겠습니까?");
                    coupon.setUserID(s.nextLine());

                    db.couponDAO.createCoupon(coupon);

                    break;

                case Menu.ADMIN_EXIT:
                    System.out.println("로그아웃합니다.");
                    stopFlag = true;
                    break;
                default:
                    System.out.println("유효한 값을 입력하세요.");
                    break;
            }
        }
    }
}
