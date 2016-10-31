package com.imarchuang.spring.integration.intertech.lab10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by mhuang on 8/19/2016.
 */
@SpringBootApplication
public class AggregatorSplitterDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(AggregatorSplitterDemoApplication.class, args);

        testSplitter(context);
    }

    private static void testSplitter(ConfigurableApplicationContext context) {

         MessageChannel channel = context.getBean("fileInputChannel", MessageChannel.class);
         Message<String> message =
         MessageBuilder.withPayload("C:\\Users\\mhuang\\Downloads\\spring_int_demo.csv").build();
         channel.send(message);
    }

}
