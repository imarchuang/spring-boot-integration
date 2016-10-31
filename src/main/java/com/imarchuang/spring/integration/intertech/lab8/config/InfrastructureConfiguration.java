package com.imarchuang.spring.integration.intertech.lab8.config;

import com.imarchuang.spring.integration.intertech.lab8.service.PrintingSA;
import com.imarchuang.spring.integration.intertech.lab8.transformer.PigLatinTransformer;
import org.springframework.context.annotation.*;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by mhuang on 9/9/2016.
 */
@Configuration
@ComponentScan("com.imarchuang.spring.integration.intertech.lab8")	//@Component
@IntegrationComponentScan("com.imarchuang.spring.integration.intertech.lab8")	//@MessagingGateway
@EnableIntegration
//@ImportResource("classpath:/META-INF/si-components_lab8.xml")
public class InfrastructureConfiguration {

    @Bean(name = "requestChannel")
    @Description("A direct channel.")
    public SubscribableChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean(name = "replyChannel")
    @Description("A direct channel.")
    public SubscribableChannel replyChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel="requestChannel", outputChannel="replyChannel")
    public PigLatinTransformer converttoString() {
        return new PigLatinTransformer();
    }

    @MessagingGateway(name = "pigLatinServiceGateway", defaultRequestChannel = "requestChannel", defaultReplyChannel = "replyChannel")
    public interface PigLatinServiceGateway {

        String translate(String english);
        //void writeToFile(@Header(FileHeaders.FILENAME) String fileName, @Header(FileHeaders.FILENAME) File directory, String data);

    }

    @Bean
    @ServiceActivator(inputChannel = "replyChannel")
    public PrintingSA printingSA() {
        return new PrintingSA();
    }



}
