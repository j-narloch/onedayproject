package com.fdmgroup.coupon.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.coupon.exception.CouponNotFoundException;
import com.fdmgroup.coupon.exception.CouponHasBeenExceededException;
import com.fdmgroup.coupon.exception.AdvancedException;
import com.fdmgroup.coupon.exception.TotalPriceTooLowException;
import com.fdmgroup.coupon.exception.UserNotFoundException;
import com.fdmgroup.coupon.model.Coupon;
import com.fdmgroup.coupon.model.User;
import com.fdmgroup.coupon.service.IUserService;

@Controller
public class UserCouponsController {
	
	private final IUserService userService;

    public UserCouponsController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/details")
    public String details(ModelMap model) {
        return "/details";
    }

    @PostMapping("/search-details")
    public String searchDetails(ModelMap model, String username) throws Exception {
        User userDetails = userService.getUserDetails(username);
        List<Coupon> userCoupons = userService.getUserCoupons(username);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("userCoupons", userCoupons);
        return "/details";
    }

    @GetMapping("/redeem-coupon")
    public String redeemCoupon() {
        return "/redeem";
    }

    @PostMapping("/redeem-coupon")
    public String redeemCoupon(ModelMap model, String username, String code) throws Exception {
        Coupon coupon = userService.redeemCouponForUser(username, code);
        model.addAttribute("coupon", coupon);
        return "/success";
    }

    @ExceptionHandler({ CouponNotFoundException.class, CouponHasBeenExceededException.class, TotalPriceTooLowException.class, UserNotFoundException.class })
    public ModelAndView handleError(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex instanceof AdvancedException ? ((AdvancedException) ex).getMessageToDisplay() : ex.getMessage());
        mav.setViewName("error");
        return mav;
    }

}
