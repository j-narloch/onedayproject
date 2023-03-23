package com.fdmgroup.coupon.exception;


public class AdvancedException extends Exception {
    private String messageToDisplay;

    public AdvancedException(String msg, String messageToDisplay) {
        super(msg);
        this.messageToDisplay = messageToDisplay;
    }

    public String getMessageToDisplay() {
        return messageToDisplay;
    }

}
