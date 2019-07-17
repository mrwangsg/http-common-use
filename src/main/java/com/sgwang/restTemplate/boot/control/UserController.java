package com.sgwang.restTemplate.boot.control;

import com.sgwang.restTemplate.boot.domain.User;
import com.sgwang.restTemplate.boot.exception.SimpleHttpException;
import com.sgwang.restTemplate.boot.service.UserService;
import com.sgwang.restTemplate.boot.tool.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @创建人 sgwang
 * @name TestController
 * @user shiguang.wang
 * @创建时间 2019/7/8
 * @描述
 */
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public List<User> list() {
        System.out.println("执行list----------");
        List<User> users = userService.findAll();

        return userService.findAll();
    }

    @GetMapping("/test")
    public Payload test() {
        System.out.println("----------执行test----------");

        ClientHttpRequestFactory test = restTemplate.getRequestFactory();
        System.out.println("SimpleClientHttpRequestFactory: " + (test instanceof SimpleClientHttpRequestFactory));
        System.out.println("HttpComponentsClientHttpRequestFactory: " + (test instanceof HttpComponentsClientHttpRequestFactory));

        Payload payload = null;

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 这里线程池的个数 会直接影响到 ReadTimeout 毕竟线程多时 会出现阻塞一直无法执行。
        for (int num = 0; num < 30; num++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int index = 0; index < 10; index++) {
                        try {
                            Payload payload = restTemplate.getForObject("http://localhost:8090/class", Payload.class);

                            System.out.println("payload: " + payload.toString());
                        } catch (SimpleHttpException exception) {
                            System.out.println("currentThread: " + Thread.currentThread() + "index: " + index + ",   exception: " + exception.getMessage());
                        }
                    }
                }
            });
        }

        return payload;
    }
}
