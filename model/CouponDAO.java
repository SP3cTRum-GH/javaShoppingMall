package javaShoppingMall.model;

import java.util.List;

public interface CouponDAO {
    List<CouponVO>selectUserCoupon(String userName);

    List<CouponSVO>selectUseableCoupon(String userName);

    void createCoupon(CouponVO coupon);

    void deleteCoupon(String useCoupon);
}
