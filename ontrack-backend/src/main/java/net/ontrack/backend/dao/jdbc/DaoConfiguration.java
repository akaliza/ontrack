package net.ontrack.backend.dao.jdbc;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class DaoConfiguration {

    @Bean
    public CacheManager cacheManager() {
        EhCacheCacheManager o = new EhCacheCacheManager();
        o.setCacheManager(ehcache().getObject());
        return o;
    }

    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        return factoryBean;
    }

}
