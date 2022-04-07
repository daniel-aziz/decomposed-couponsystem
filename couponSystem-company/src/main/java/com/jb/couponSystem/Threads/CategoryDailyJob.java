package com.jb.couponSystem.Threads;

import com.jb.couponSystem.Beans.Category;
import com.jb.couponSystem.Beans.CategoryEntity;
import com.jb.couponSystem.Repositories.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this thread class used for fixed interval of adding categories to the category table in the DB
 */
@Component
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class CategoryDailyJob {
    // FIELDS
    private final CategoryEntityRepository categoryEntityRepository;  // initialize the repository for thread to use
    private final String CRON_TIME = "0 0 * * * ?"; // * * * * * ? -> every minute | 0 0 * * * ? -> at 00:00
    private final String TIME_ZONE = "Asia/Jerusalem";

    // METHODS

    /**
     * this method will delete expired coupons by timed cron job
     */
    @Async
    @Scheduled(cron = CRON_TIME, zone = TIME_ZONE)
    public void dailyJob() {

        categoryEntityRepository.deleteAll();

        List<String> categoryEntities = Arrays.stream(Category.values())
                .map(Category::getDescription)
                .collect(Collectors.toList());

        categoryEntities.forEach((item) -> {
            CategoryEntity category = CategoryEntity.builder().id(Category.valueOf(item).ordinal()+1).category(item).build();
            categoryEntityRepository.save(category);
        });

    }


}
