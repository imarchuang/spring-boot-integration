package com.imarchuang.spring.integration.intertech.lab1.producer;

import com.imarchuang.spring.integration.sample.producer.ProducerApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by mhuang on 9/9/2016.
 */
public class MessageProducerApp implements CommandLineRunner {

    public static MessageChannel channel;

    public void run(String... args) throws Exception {
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

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MessageProducerApp.class, args);
    }
}
