package com.fdmgroup.coupon.repository;

import com.fdmgroup.coupon.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findAllByUserUsernameIgnoreCase(String username);
    Optional<Coupon> findByUserUsernameIgnoreCaseAndCodeIgnoreCase(String username, String code);
}
