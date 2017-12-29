package com.jiespace.common.config;

import lombok.Data;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by huangmingjie on 2017/12/25.
 */
//@Configuration
//@ConfigurationProperties(prefix = "spring.solr")
//@Data
public class SolrConfig {
    
    /*private CloudSolrServer solrServer;
    
    private String host;
    private String zkHost;
    private String defaultCollection;
    
    @PreDestroy
    public void close() {
        if (this.solrServer != null) {
            try {
                this.solrServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Bean
    public CloudSolrServer SolrServer() {
        if (StringUtils.hasText(zkHost)) {
            solrServer = new CloudSolrServer(zkHost);
            solrServer.setDefaultCollection(defaultCollection);
        }
        return this.solrServer;
    }*/
}
