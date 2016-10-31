package com.imarchuang.spring.integration.sample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Created by mhuang on 8/19/2016.
 */

@ConfigurationProperties(prefix = "service", ignoreUnknownFields = false)
@ManagedResource
public class ServiceProperties {

    private String greeting = "Hello";

    @ManagedAttribute
    public String getGreeting() {
        return this.greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
