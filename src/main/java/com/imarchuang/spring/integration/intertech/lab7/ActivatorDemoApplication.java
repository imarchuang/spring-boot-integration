package com.imarchuang.spring.integration.intertech.lab7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by mhuang on 8/19/2016.
 */
@SpringBootApplication
public class ActivatorDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ActivatorDemoApplication.class, args);
    }

}
