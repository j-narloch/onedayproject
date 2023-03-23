package com.fdmgroup.coupon.exception;

public class UserNotFoundException extends AdvancedException {
    public UserNotFoundException(String message, String detailedMessage) {
        super(message, detailedMessage);
    }

}
