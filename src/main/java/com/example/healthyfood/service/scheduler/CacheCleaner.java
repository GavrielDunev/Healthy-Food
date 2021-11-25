package com.example.healthyfood.service.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheCleaner {

    @CacheEvict(cacheNames = "latestRecipes", allEntries = true)
    public void removeCachedRecipes() {}

}
