package com.imarchuang.spring.integration.intertech.lab1;

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
public class ChannelDemoApplication {

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(ChannelDemoApplication.class, args);
        ConfigurableApplicationContext context =
                SpringApplication.run(ChannelDemoApplication.class, args);

        sendDummyMessages(context);

    }

    private static void sendDummyMessages(ConfigurableApplicationContext context){

        MessageChannel channel = context.getBean("messageChannel", MessageChannel.class);

        Message<String> message1 = MessageBuilder.withPayload(
                "Hello world - one!").build();
        Message<String> message2 = MessageBuilder.withPayload(
                "Hello world - two!").build();
        Message<String> message3 = MessageBuilder.withPayload(
                "Hello world - three!").build();
        System.out.println("sending message1");
        channel.send(message1);
        System.out.println("sending message2");
        channel.send(message2);
        System.out.println("sending message3");
        channel.send(message3);
        System.out.println("done sending messages");
    }

}
