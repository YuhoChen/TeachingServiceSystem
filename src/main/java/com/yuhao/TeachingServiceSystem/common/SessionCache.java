package com.yuhao.TeachingServiceSystem.common;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


@Component
public class SessionCache {
    // key: userId, value: sessionId
    private Cache<Long, String> cache;

    // key: sessionId, value: userid
    private Cache<String, Long> cache2;

    @PostConstruct
    public void init() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();

        cache2 = CacheBuilder.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }

    public boolean exists(String sessionId) {
        try {
            Long value = cache2.get(sessionId, new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    return -1L;
                }
            });
            if (value == -1L) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("请先登陆");
        }
    }

    public void set(Long userId, String sessionId) {
        String oldCache = cache.getIfPresent(userId);
        if (StringUtils.isNotBlank(oldCache)) {
            cache2.invalidate(oldCache);
        }
        cache.put(userId, sessionId);
        cache2.put(sessionId, userId);
    }

    public void touch(String sessionId) {
        Long userId = cache2.getIfPresent(sessionId);
        if (userId != null && userId != -1) {
            cache.put(userId, sessionId);
            cache2.put(sessionId, userId);
        }
    }
    public Long getUserId(String sessionId) {
        return cache2.getIfPresent(sessionId);
    }

    public String getSessionId(Long userId) {
        return cache.getIfPresent(userId);
    }
}

