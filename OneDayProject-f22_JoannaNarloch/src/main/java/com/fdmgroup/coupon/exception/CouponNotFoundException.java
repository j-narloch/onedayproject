package com.fdmgroup.coupon.exception;

public class CouponNotFoundException extends AdvancedException {
    public CouponNotFoundException(String message, String detailedMessage) {
        super(message, detailedMessage);
    }
}
