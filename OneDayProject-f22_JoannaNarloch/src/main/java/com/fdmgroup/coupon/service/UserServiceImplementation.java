package com.fdmgroup.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fdmgroup.coupon.exception.CouponNotFoundException;
import com.fdmgroup.coupon.exception.CouponHasBeenExceededException;
import com.fdmgroup.coupon.exception.TotalPriceTooLowException;
import com.fdmgroup.coupon.exception.UserNotFoundException;
import com.fdmgroup.coupon.model.Coupon;
import com.fdmgroup.coupon.model.User;
import com.fdmgroup.coupon.repository.CouponRepository;
import com.fdmgroup.coupon.repository.UserRepository;

@Service
public class UserServiceImplementation implements IUserService {
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    public UserServiceImplementation(UserRepository userRepository, CouponRepository couponRepository) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
    }

    @Override
    public List<Coupon> getUserCoupons(String userName) {
        return couponRepository.findAllByUserUsernameIgnoreCase(userName);
    }

    @Override
    public User getUserDetails(String userName) throws UserNotFoundException {
        return userRepository.findUserByUsernameIgnoreCase(userName).orElseThrow(() -> new UserNotFoundException("No such user", "There is no such user as " + userName));
    }

    @Override
    public Coupon redeemCouponForUser(String username, String code) throws Exception {
        Coupon coupon = couponRepository
            .findByUserUsernameIgnoreCaseAndCodeIgnoreCase(username, code)
            .orElseThrow(() -> new CouponNotFoundException("Coupon not found", "Coupon with name " + code + " not found"));
        User user = userRepository.findUserByUsernameIgnoreCase(username).orElseThrow(() -> new UserNotFoundException("User not found", "User " + username + " does not exist!"));
        validateIfPossibleToRedeem(user, coupon);
        coupon.setAllowedUsages(coupon.getAllowedUsages() - 1);
        coupon.setCurrentUsages(coupon.getCurrentUsages() + 1);
        user.setTotalPrice(user.getTotalPrice() - coupon.getValue());
        couponRepository.save(coupon);
        userRepository.save(user);
        return coupon;

    }

    private void validateIfPossibleToRedeem(User user, Coupon coupon) throws TotalPriceTooLowException, CouponHasBeenExceededException {
        if (user.getTotalPrice() < coupon.getValue()) {
            throw new TotalPriceTooLowException("Total price exception", "Sorry, the total value is too low.");
        }
        if (coupon.getAllowedUsages() <= 0) {
            throw new CouponHasBeenExceededException("Coupon has been exceeded", "The allowed coupon usages has been exceeded.");
        }
    }
}
