package com.sgwang.nio;

import com.sgwang.nio.client.Client;
import com.sgwang.nio.server.Server;

import java.util.Scanner;

/**
 * @创建人 sgwang
 * @name Test
 * @user 91119
 * @创建时间 2019/7/28
 * @描述
 */

public class Test {
    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        //运行服务器
        Server.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine())) ;
    }
}

