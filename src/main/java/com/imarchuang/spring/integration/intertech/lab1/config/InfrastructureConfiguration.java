package com.imarchuang.spring.integration.intertech.lab1.config;

import org.springframework.context.annotation.*;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.support.PeriodicTrigger;

/**
 * Created by mhuang on 9/9/2016.
 */
@Configuration
@ComponentScan("com.imarchuang.spring.integration.intertech.lab1")    //@Component
//@IntegrationComponentScan("xpadro.spring.integration.gateway")	//@MessagingGateway
@EnableIntegration
@ImportResource("classpath:/META-INF/si-components_lab1.xml")
public class InfrastructureConfiguration {

    @Bean
    @Description("A direct channel.")
    public MessageChannel directChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("A pub-sub channel")
    public MessageChannel pubsubChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean(name = "messageChannel")
    @Description("A pollable channel")
    public MessageChannel pollableChannel() {
        return new QueueChannel(2);
    }

    //    <int:poller id="defaultPoller" default="true"
    //    max-messages-per-poll="5" fixed-rate="200" />
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    @Description("A default poller")
    public PollerMetadata defaultPoller() {

//        PollerMetadata pollerMetadata = new PollerMetadata();
//        pollerMetadata.setMaxMessagesPerPoll(5);
//        pollerMetadata.setTrigger(new PeriodicTrigger(200));
//        return pollerMetadata;

        return Pollers.fixedRate(200).maxMessagesPerPoll(5).get();
    }

}
