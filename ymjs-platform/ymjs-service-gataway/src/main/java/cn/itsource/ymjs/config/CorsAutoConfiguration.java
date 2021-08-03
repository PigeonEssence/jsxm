package cn.itsource.ymjs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
//@ConditionalOnBean(GlobalCorsProperties.class)
public class CorsAutoConfiguration {
    @Autowired
    private  GlobalCorsProperties globalCorsProperties;
    
    @Bean
    public CorsWebFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        globalCorsProperties.getCorsConfigurations()
                .forEach((path,corsConfiguration)->source.registerCorsConfiguration(path, corsConfiguration));
        return new CorsWebFilter(source);
    }
}