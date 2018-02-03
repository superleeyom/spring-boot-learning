package com.leeyom.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 注入多数据源
 * @author Leeyom Wang
 * @date 2018年02月03日 12:15
 */
@Configuration
public class MultiDataSourceConfig {
    /**
     * 加载数据源1，使用注解：@Primary，指定一个默认的主数据源
     * @return
     */
    @Primary
    @Bean(name = "oneDataSource")
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 加载数据源2
     * @return
     */
    @Bean(name = "twoDataSource")
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource dataSourceTwo() {
        return DruidDataSourceBuilder.create().build();
    }
}
