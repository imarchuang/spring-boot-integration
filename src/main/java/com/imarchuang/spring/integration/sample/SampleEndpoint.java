package com.imarchuang.spring.integration.sample;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by mhuang on 8/19/2016.
 */
@MessageEndpoint
public class SampleEndpoint {
    private final HelloWorldService helloWorldService;

    public SampleEndpoint(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @ServiceActivator
    public String hello(File input) throws Exception {
        FileInputStream in = new FileInputStream(input);
        String name = new String(StreamUtils.copyToByteArray(in));
        in.close();
        return this.helloWorldService.getHelloMessage(name);
    }
}
