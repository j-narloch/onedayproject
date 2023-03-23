package com.fdmgroup.coupon.dataimport;

import com.fdmgroup.coupon.model.Coupon;
import com.fdmgroup.coupon.model.User;
import com.fdmgroup.coupon.repository.CouponRepository;
import com.fdmgroup.coupon.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class DataImport implements ApplicationRunner {
	
	private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    public DataImport(UserRepository userRepository, CouponRepository couponRepository) {
        this.userRepository = userRepository;
        this.couponRepository = couponRepository;
    }

   
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User(null, "asia", "Joanna", "Narloch", "joannanarloch@example.com", 100.0);
        userRepository.save(user1);
        User user2 = new User(null, "janek", "Jan", "Dzbanek", "jandzbanek@example.com", 100.0);
        userRepository.save(user2);

        Coupon couponNr1 = new Coupon(null, "SALE_C", 10.0, 1, 0, user1);
        couponRepository.save(couponNr1);
        Coupon couponNr2 = new Coupon(null, "VALENTINE_X", 11.0, 1, 0, user1);
        couponRepository.save(couponNr2);
        Coupon couponNr3 = new Coupon(null, "SALE_D", 12.0, 1, 0, user2);
        couponRepository.save(couponNr3);
        Coupon couponNr4 = new Coupon(null, "VALENTINE_Y", 10.0, 1, 0, user2);
        couponRepository.save(couponNr4);
        
    }


}
