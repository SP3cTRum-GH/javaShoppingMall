package javaShoppingMall.view.user;

import javaShoppingMall.model.CouponVO;
import javaShoppingMall.model.Database;
import javaShoppingMall.model.PaylogVO;
import javaShoppingMall.model.UserVO;

import java.util.List;
import java.util.Scanner;

public class MyPage {
    public void myPage(UserVO user, Database db){
        Scanner s = new Scanner(System.in);
        System.out.println("마이페이지");
        System.out.println(user);
        System.out.println("1.결제내역보기 2.보유쿠폰보기 3.메뉴선택화면으로");
        int choice = Integer.parseInt(s.nextLine());
        switch (choice){
            case 1:
                System.out.println("결제내역");
                List<PaylogVO> receipt = db.paylogDAO.selectUserPay(user.getId());
                for (PaylogVO i : receipt){
                    System.out.println(i);
                }
                break;
            case 2:
                System.out.println("보유 쿠폰목록");
                List<CouponVO> userCoupons = db.couponDAO.selectUserCoupon(user.getId());
                for (CouponVO i : userCoupons){
                    System.out.println(i);
                }
                break;
            default:
        }
    }
}
