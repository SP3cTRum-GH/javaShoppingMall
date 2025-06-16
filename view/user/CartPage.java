package javaShoppingMall.view.user;

import javaShoppingMall.model.*;

import java.util.List;
import java.util.Scanner;

public class CartPage {
    public void cartPage(Database db, UserVO user){
        List<CartSVO> cartList = db.cartDAO.selectUserCart(user.getId());
        Scanner s = new Scanner(System.in);

        if(cartList.isEmpty()){
            System.out.println("장바구니 목록이 비어있습니다.");
            return;
        }
        boolean stopflag = false;
        while(!stopflag) {
            for(CartSVO i : cartList){
                System.out.println(i);
            }
            System.out.println("1.구매, 2.삭제, 3.장바구니 초기화 4.쿠폰적용 5.메뉴로 돌아가기");
            int choice = Integer.parseInt(s.nextLine());

            switch (choice) {
                case 1:
                    db.paylogDAO.pay(user.getId(), cartList);
                    db.cartDAO.deleteAllCart(user.getId());
                    cartList.clear();
                    return;

                case 2:
                    System.out.println("몇번째 장바구니 품목을 삭제할까요?(여러개 동시 선택가능 공백으로 구분): ");
                    String[] str = s.nextLine().split(" ");
                    int deleteSize = str.length;
                    int[] deleteItems = new int[deleteSize];
                    for (int i = 0; i < deleteSize; i++) {
                        deleteItems[i] = cartList.get(Integer.parseInt(str[i]) - 1).getIndex();
                    }
                    db.cartDAO.deleteSelecteItems(deleteItems);
                    cartPage(db,user);
                    break;
                case 3:
                    db.cartDAO.deleteAllCart(user.getId());
                    System.out.println("장바구니 초기화 완료");
                    cartPage(db,user);
                    break;
                case 4:
                    List<CouponSVO> useableCoupon = db.couponDAO.selectUseableCoupon(user.getId());
                    if (useableCoupon.isEmpty()) {
                        System.out.println("사용가능한 쿠폰이 없습니다.");
                    } else {
                        System.out.println("사용가능 쿠폰목록");
                        for (CouponSVO i : useableCoupon) {
                            System.out.println(i);
                        }
                        System.out.println("몇번째 쿠폰을 사용하시겠습니까?");
                        int useCoupon = Integer.parseInt(s.nextLine());

                        for (CartSVO i : cartList) {
                            if (i.getName().equals(useableCoupon.get(useCoupon - 1).getItemName())) {
                                i.setPrice((int)(i.getPrice() * (1-(useableCoupon.get(useCoupon - 1).getValue() * 0.01))));
                            }
                        }
                        db.couponDAO.deleteCoupon(useableCoupon.get(useCoupon-1).getCode());
                    }

                    break;
                default:
                    stopflag = true;
            }
        }
    }
}
