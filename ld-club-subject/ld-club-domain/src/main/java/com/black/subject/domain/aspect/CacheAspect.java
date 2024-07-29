package com.black.subject.domain.aspect;


import com.black.subject.common.enums.CacheType;
import com.black.subject.common.interfaces.multipleCache;
import com.black.subject.domain.config.splConfig;
import com.black.subject.domain.redis.RedisUtils;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * 多级缓存切面类
 */
@Slf4j
@Component
@Aspect
@AllArgsConstructor
public class CacheAspect {
    private final Cache cache;
    private final splConfig ElParser;
    public static final String CacheConstant = "ldclub";
    @Around("@annotation(com.black.subject.common.interfaces.multipleCache)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        //拼接解析springEl表达式的map
        String[] paramNames = signature.getParameterNames();
        Object[] args = point.getArgs();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            treeMap.put(paramNames[i],args[i]);
        }

        multipleCache annotation = method.getAnnotation(multipleCache.class);
        String elResult = ElParser.parse(annotation.key(), treeMap);
        String realKey = annotation.cacheName() + CacheConstant + elResult;

        //强制更新
        if (annotation.type()== CacheType.PUT){
            Object object = point.proceed();
            RedisUtils.set(realKey, object,annotation.l2TimeOut(), TimeUnit.SECONDS);
            cache.put(realKey, object);
            return object;
        }
        //删除
        else if (annotation.type()== CacheType.DELETE){
            RedisUtils.del(realKey);
            cache.invalidate(realKey);
            return point.proceed();
        }

        //读写，查询Caffeine
        Object caffeineCache = cache.getIfPresent(realKey);
        if (Objects.nonNull(caffeineCache)) {
            log.info("数据从Caffeine中获取");
            return caffeineCache;
        }

        //查询Redis
        Object redisCache = RedisUtils.get(realKey);
        if (Objects.nonNull(redisCache)) {
            log.info("数据从Redis中获取");
            cache.put(realKey, redisCache);
            return redisCache;
        }

        log.info("数据从数据库中获取");
        Object object = point.proceed();
        if (Objects.nonNull(object)){
            //写入Redis
            RedisUtils.set(realKey, object,annotation.l2TimeOut(), TimeUnit.MINUTES);
            //写入Caffeine
            cache.put(realKey, object);
        }
        return object;
    }
}
