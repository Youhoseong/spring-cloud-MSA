package com.example.apigatewayservice.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    //@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        // request와 response에 대해 FILTER를 적용할 수 있다.
        // YML파일과 CONFIG 방법 중 선택, 둘 다 사용하면 config 파일이 override한다.
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-request1", "first-request-header")
                                        .addResponseHeader("first-response1", "first-response-header")
                        )
                        .uri("http://localhost:8081")
                )
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request1", "second-request-header")
                                .addResponseHeader("second-response1", "second-response-header")
                        )
                        .uri("http://localhost:8082")
                )
                .build();


    }
}
