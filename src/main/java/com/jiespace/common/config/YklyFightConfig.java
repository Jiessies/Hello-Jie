package com.jiespace.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by huangmingjie on 2017/10/23.
 */
@Component
@Configuration
public class YklyFightConfig {
    
//    @Value("${ykly-fight-url}")
    private String Fight_Url;
    
    public String getFight_Url() {
        return Fight_Url;
    }
    
    public void setFight_Url(String fight_Url) {
        Fight_Url = fight_Url;
    }
}
