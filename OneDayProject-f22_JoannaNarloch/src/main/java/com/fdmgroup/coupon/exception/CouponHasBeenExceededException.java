package com.fdmgroup.coupon.exception;

public class CouponHasBeenExceededException extends AdvancedException {
    public CouponHasBeenExceededException(String message, String detailedMessage) {
        super(message, detailedMessage);
    }
}
