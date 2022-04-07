package com.jb.couponSystem.CLR;

import com.jb.couponSystem.Repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class GeneralTest implements CommandLineRunner {
    private final CouponRepository couponRepository;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("-------------------------------------------------------");


    }
}
