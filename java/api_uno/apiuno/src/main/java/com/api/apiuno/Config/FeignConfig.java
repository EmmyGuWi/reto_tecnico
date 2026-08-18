package com.api.apiuno.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig {


      @Bean
    public RequestInterceptor requestInterceptor() {
  /* 
        return requestTemplate -> {

            String authorization =
                request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authorization != null &&
                authorization.startsWith("Bearer ")) {

                requestTemplate.header(
                    HttpHeaders.AUTHORIZATION,
                    authorization
                );
            }
        }; */

 return requestTemplate -> {

            ServletRequestAttributes attributes =
                    (ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes();

            if (attributes == null) {
                return;
            }

            HttpServletRequest request =
                    attributes.getRequest();

            String authorization =
                    request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authorization != null &&
                    authorization.startsWith("Bearer ")) {

                requestTemplate.header(
                        HttpHeaders.AUTHORIZATION,
                        authorization
                );
            }
        };
    }
        
    
    
}
