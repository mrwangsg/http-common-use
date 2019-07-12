package com.sgwang.restTemplate.boot.config;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;

/**
 * @创建人 sgwang
 * @name SimpleClientBean
 * @user shiguang.wang
 * @创建时间 2019/7/12
 * @描述
 */
@Component
public class SimpleClientBean implements ClientFactory{

    @Override
    public ClientHttpRequestFactory execute() {
        return null;
    }

}
