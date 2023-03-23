package com.fdmgroup.coupon.service;

import java.util.List;

import com.fdmgroup.coupon.exception.CouponHasBeenExceededException;
import com.fdmgroup.coupon.exception.TotalPriceTooLowException;
import com.fdmgroup.coupon.model.Coupon;
import com.fdmgroup.coupon.model.User;

public interface IUserService {

    List<Coupon> getUserCoupons(String userName);

    User getUserDetails(String userName) throws Exception;

    Coupon redeemCouponForUser(String username, String code) throws Exception, CouponHasBeenExceededException, TotalPriceTooLowException;
}
