package com.imarchuang.spring.integration.amqp;

import com.imarchuang.spring.integration.amqp.config.InfrastructureConfiguration;
import com.imarchuang.spring.integration.amqp.receiver.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by mhuang on 9/12/2016.
 */
@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(InfrastructureConfiguration.queueName, "Hello from RabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        context.close();

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Runner.class, args);
    }
}
