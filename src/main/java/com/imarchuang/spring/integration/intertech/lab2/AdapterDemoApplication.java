package com.imarchuang.spring.integration.intertech.lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by mhuang on 8/19/2016.
 */
@SpringBootApplication
public class AdapterDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(AdapterDemoApplication.class, args);

        Object consumer = context.getBean("fileWritingMessageHandler");

        System.out.println(consumer.getClass());
    }

}
