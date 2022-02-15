package com.jb.couponSystem;

import com.jb.couponSystem.Utils.ArtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class CouponSystemApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);
		System.out.println(ArtUtil.LOCALHOST);
	}

}
