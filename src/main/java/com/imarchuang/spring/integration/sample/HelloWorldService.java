package com.imarchuang.spring.integration.sample;

import org.springframework.stereotype.Service;

/**
 * Created by mhuang on 8/19/2016.
 */

@Service
public class HelloWorldService {

    private final ServiceProperties configuration;

    public HelloWorldService(ServiceProperties configuration) {
        this.configuration = configuration;
    }

    public String getHelloMessage(String name) {
        return this.configuration.getGreeting() + " " + name;
    }

}
