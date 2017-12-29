package com.jiespace.common.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * Created by huangmingjie on 2017/12/22.
 */
@Configuration
public class Config {
    /*@Value("${connection.timeout}")
    private int connectionTimeout;
    
    @Bean
    public AsyncRestTemplate getAsyncRestTemplate() {
        
        HttpComponentsAsyncClientHttpRequestFactory asyncRequestFactory = new HttpComponentsAsyncClientHttpRequestFactory();
        asyncRequestFactory.setHttpAsyncClient(asyncHttpClient());
        return new AsyncRestTemplate(asyncRequestFactory);
    }
    
    @Bean
    public CloseableHttpAsyncClient asyncHttpClient() {
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setConnectTimeout(connectionTimeout).setSoTimeout(connectionTimeout).build();
        return HttpAsyncClients.custom().setDefaultIOReactorConfig(ioReactorConfig).build();
    }*/
}
