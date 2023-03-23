package com.fdmgroup.coupon.exception;

public class TotalPriceTooLowException extends AdvancedException {
    public TotalPriceTooLowException(String message, String detailedMessage) {
        super(message, detailedMessage);
    }

}
