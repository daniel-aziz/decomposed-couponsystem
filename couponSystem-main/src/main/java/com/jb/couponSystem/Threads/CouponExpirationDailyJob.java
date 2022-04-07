package com.jb.couponSystem.Threads;

import com.jb.couponSystem.Repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
/**
 * this thread class used for fixed interval of deletion of coupons from the DB system
 */
public class CouponExpirationDailyJob {
    // FIELDS
    private final CouponRepository couponRepository;  // initialize the repository for thread to use
    private final String CRON_TIME = "0 0 * * * ?"; // * * * * * ? -> every minute | 0 0 * * * ? -> at 00:00
    private final String TIME_ZONE = "Asia/Jerusalem";

    // METHODS

    /**
     * this method will delete expired coupons by timed cron job
     */
    @Async
    @Scheduled(cron = CRON_TIME, zone = TIME_ZONE)
    public void dailyJob(){
            couponRepository.deleteExpiredCoupons();

    }




}
