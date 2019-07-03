package com.sgwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @创建人 sgwang
 * @name StartApp
 * @user shiguang.wang
 * @创建时间 2019/7/3
 * @描述
 */
public class StartApp {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, world!");

        File file = new File("src/main/resources/in.txt");
        if (file.exists()){
            System.out.println("找到正确路径！");
        }

        File outFile = new File("src/main/resources/out.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(outFile);

        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1){
            outputStream.write(bytes);
        }

    }

}
