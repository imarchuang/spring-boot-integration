package com.imarchuang.spring.integration.intertech.lab1;

import com.imarchuang.spring.integration.intertech.lab1.producer.MessageProducerApp;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageChannel;

/**
 * Created by mhuang on 9/9/2016.
 */
public class ChannelDemoApplicationTest {

    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void start() throws Exception {
        context = SpringApplication.run(ChannelDemoApplication.class);
    }

    @AfterClass
    public static void stop() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void testMessageSend() throws Exception {
        MessageProducerApp.channel = context.getBean("messageChannel", MessageChannel.class);
        SpringApplication.run(MessageProducerApp.class, "World");
    }

}