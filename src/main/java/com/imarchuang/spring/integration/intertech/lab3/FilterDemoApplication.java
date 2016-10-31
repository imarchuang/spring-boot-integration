package com.imarchuang.spring.integration.intertech.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by mhuang on 8/19/2016.
 */
@SpringBootApplication
public class FilterDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(FilterDemoApplication.class, args);
    }

}
