
package com.mycompany.urlshortener.configurations;

import com.mycompany.urlshortener.middleware.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class logConfiguration implements WebMvcConfigurer{
    
    public logConfiguration() {
        super();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
    }
    
}
