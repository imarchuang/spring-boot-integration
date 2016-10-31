package com.imarchuang.spring.integration.amqp;

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
public class RabbitDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =
                SpringApplication.run(RabbitDemoApplication.class, args);
        //Runner.main(args);
    }

}
