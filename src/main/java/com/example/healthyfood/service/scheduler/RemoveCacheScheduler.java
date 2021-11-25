package com.example.healthyfood.service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveCacheScheduler {

    private final CacheCleaner cacheCleaner;

    public RemoveCacheScheduler(CacheCleaner cacheCleaner) {
        this.cacheCleaner = cacheCleaner;
    }

    @Scheduled(cron = "0 0 */2 * * *")
    public void clearCacheEveryTwoHours() {
        this.cacheCleaner.removeCachedRecipes();
    }
}
