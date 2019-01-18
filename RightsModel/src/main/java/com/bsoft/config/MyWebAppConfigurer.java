package com.bsoft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * MyWebAppConfigurer
 * @description 解决跨域问题的配置类
 * @author zk
 * @date 2018年12月5日 下午5:46:17
 * @version 1.0
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
