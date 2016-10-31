package com.imarchuang.spring.integration.intertech.lab9;

import com.imarchuang.spring.integration.intertech.lab9.config.InfrastructureConfiguration;
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
public class SplitterDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SplitterDemoApplication.class, args);

        testSplitter(context);
    }

    private static void testSplitter(ConfigurableApplicationContext context) {

         MessageChannel channel = context.getBean("fileInputChannel", MessageChannel.class);
         Message<String> message =
         MessageBuilder.withPayload("C:\\Users\\mhuang\\Downloads\\Fnac-ES-CMSmatch-20160913-2.csv").build();
         channel.send(message);
    }

}
