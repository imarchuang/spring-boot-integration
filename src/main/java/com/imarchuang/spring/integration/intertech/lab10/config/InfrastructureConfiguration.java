package com.imarchuang.spring.integration.intertech.lab10.config;

import com.imarchuang.spring.integration.intertech.lab10.service.PrintingCsvRecordSA;
import com.imarchuang.spring.integration.intertech.lab10.service.RecordActivator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by mhuang on 9/9/2016.
 */
@Configuration
@ComponentScan("com.imarchuang.spring.integration.intertech.lab10")	//@Component
@IntegrationComponentScan("com.imarchuang.spring.integration.intertech.lab10")	//@MessagingGateway
@EnableIntegration
public class InfrastructureConfiguration {

    @Bean(name = "fileInputChannel")
    @Description("A direct channel.")
    public SubscribableChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean(name = "csvObjectInputChannel")
    @Description("A direct channel.")
    public SubscribableChannel csvObjectInputChannel() {
        return new DirectChannel();
    }

    @Bean(name = "aggregatedCsvObjectOutputChannel")
    @Description("A direct channel.")
    public SubscribableChannel aggregatedCsvObjectOutputChannel() {
        return new DirectChannel();
    }

//    @Bean
//    @Transformer(inputChannel="requestChannel", outputChannel="replyChannel")
//    public PigLatinTransformer converttoString() {
//        return new PigLatinTransformer();
//    }
//
//    @MessagingGateway(name = "pigLatinServiceGateway", defaultRequestChannel = "requestChannel", defaultReplyChannel = "replyChannel")
//    public interface PigLatinServiceGateway {
//
//        String translate(String english);
//        //void writeToFile(@Header(FileHeaders.FILENAME) String fileName, @Header(FileHeaders.FILENAME) File directory, String data);
//
//    }

//    @Bean
//    @ServiceActivator(inputChannel = "csvObjectInputChannel", outputChannel = "csvObjectProcessedChannel")
//    public PrintingCsvRecordSA printingSA() {
//        return new PrintingCsvRecordSA();
//    }

    @Bean
    @ServiceActivator(inputChannel = "csvObjectInputChannel", outputChannel = "csvObjectProcessedChannel")
    public RecordActivator recordActivator() {
        return new RecordActivator();
    }



}
