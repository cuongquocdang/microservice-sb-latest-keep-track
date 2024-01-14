package com.example.microservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

@Configuration
public class HttpClientConfig {

    @Bean
    public TestClient testClient(final RestClient.Builder restClientBuilder,
                                 final Logbook logbook) {

        var webClient = restClientBuilder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .requestInterceptor(new LogbookClientHttpRequestInterceptor(logbook))
                .build();

        var httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(webClient))
                        .build();

        return httpServiceProxyFactory.createClient(TestClient.class);
    }
}