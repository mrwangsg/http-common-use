package com.sgwang.restTemplate.boot.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @创建人 sgwang
 * @name RestTemplateConfig
 * @user 91119
 * @创建时间 2019/7/8
 * @描述
 */
@Configuration
public class RestTemplateConfig {

    @Value("${rest.template.http.request.factory.strategy}")
    private String requestFactory;

    @Autowired
    private SimpleClientBean simpleClient;

    @Autowired
    private HTTPClientBean httpClient;

    @Bean
    public RestTemplate restTemplate() {
        ClientHttpRequestFactory httpRequestFactory = getHttpRequestFactory();

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        return restTemplate;
    }

    // 选择一个策略生成 相应一个工厂
    private ClientHttpRequestFactory getHttpRequestFactory() {
        ClientHttpRequestFactory httpRequestFactory = null;

        // 穷举有 SimpleClient、HttpClient、OkHttp3Client、Netty4Client
        switch (requestFactory) {
            case "SimpleClient":
                httpRequestFactory = simpleClient.execute();
                break;
            case "HttpClient":
                httpRequestFactory = httpClient.execute();
                break;
            case "OkHttp3Client":
                break;
            case "Netty4Client":
                break;
            default:
                break;
        }

        return httpRequestFactory;
    }


}
