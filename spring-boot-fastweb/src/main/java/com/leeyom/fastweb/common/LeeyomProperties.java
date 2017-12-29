package com.leeyom.fastweb.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * @author Leeyom Wang
 * @date 2017年12月29日 18:18
 */
@Component
public class LeeyomProperties {

    @Value("${com.leeyom.title}")
    private String title;
    @Value("${com.leeyom.description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
