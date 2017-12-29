package com.jiespace.common.config;

import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Created by huangmingjie on 2017/10/17.
 */
@Component
@Configuration
public class RestTemplateConfig {
    @Value("${http.pooling.max-totle}")
    private int maxTotle;
    
    @Value("${http.pooling.max-per-route}")
    private int maxPerRoute;
    
    @Value("${http.timeout.socket.short}")
    private int shortSoTimeout;
    
    @Value("${http.timeout.socket.long}")
    private int longSoTimeout;
    
    @Value("${http.timeout.connect}")
    private int connectTimeout;
    
    @Value("${http.timeout.connect.request}")
    private int connectReqTimeout;
    
    @Bean(name = "sRestTemplate")
    public RestTemplate sRestTemplate() {
        return new RestTemplate(httpRequestFactory(shortSoTimeout));
    }
    
    @Bean(name = "lRestTemplate")
    public RestTemplate lRestTemplate() {
        return new RestTemplate(httpRequestFactory(Integer.valueOf(longSoTimeout)));
    }
    
    /*@Bean(name = "asyncRestTemplate")
    public AsyncRestTemplate asyncRestTemplate(){
        return new AsyncRestTemplate((AsyncClientHttpRequestFactory) httpRequestFactory(shortSoTimeout));
    }*/
    
    private HttpComponentsClientHttpRequestFactory httpRequestFactory(int socketTimeout) {
        PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager();
        phccm.setMaxTotal(maxTotle);
        phccm.setDefaultMaxPerRoute(maxPerRoute);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setConnectionManager(phccm).build());
        factory.setConnectionRequestTimeout(connectReqTimeout);
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(socketTimeout);
        return factory;
    }
}
