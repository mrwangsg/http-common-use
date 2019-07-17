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
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @创建人 sgwang
 * @name HTTPClientBean
 * @user shiguang.wang
 * @创建时间 2019/7/12
 * @描述
 */
@Component
public class HTTPClientBean implements ClientFactory{

    @Override
    public ClientHttpRequestFactory execute() {
        HttpClient httpClient = httpClient();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    private HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(3);
        connectionManager.setDefaultMaxPerRoute(3);
        // 官方推荐使用这个来检查永久链接的可用性，而不推荐每次请求的时候才去检查，而是启动后台线程 定时清理废弃链接
        connectionManager.setValidateAfterInactivity(2000);
        connectionManager.closeIdleConnections(100, TimeUnit.MILLISECONDS);
        connectionManager.closeExpiredConnections();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000) //服务器返回数据(response)的时间，超过抛出read timeout
                .setConnectTimeout(3000) //连接上服务器(握手成功)的时间，超出抛出connect timeout
                .setConnectionRequestTimeout(10000)//从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

}
