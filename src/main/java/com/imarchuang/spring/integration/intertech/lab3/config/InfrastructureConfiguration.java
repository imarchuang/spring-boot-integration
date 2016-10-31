package com.imarchuang.spring.integration.intertech.lab3.config;

import org.springframework.context.annotation.*;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

import java.io.File;

/**
 * Created by mhuang on 9/9/2016.
 */
@Configuration
@ComponentScan("com.imarchuang.spring.integration.intertech.lab3")	//@Component
@IntegrationComponentScan("com.imarchuang.spring.integration.intertech.lab3")	//@MessagingGateway
@EnableIntegration
@ImportResource("classpath:/META-INF/si-components_lab3.xml")
public class InfrastructureConfiguration {

    @Bean(name = "inboundChannel")
    @Description("A direct channel.")
    public SubscribableChannel directChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("A direct channel.")
    public SubscribableChannel outboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("A pub-sub channel")
    public MessageChannel pubsubChannel() {
        return new PublishSubscribeChannel();
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

    @Bean
    @InboundChannelAdapter(value = "inboundChannel", poller = @Poller(fixedRate = "5000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        //source.setDirectory(new File("file:c://inbound"));
        source.setDirectory(new File("data/source/inbound"));
        source.setFilter(new SimplePatternFileListFilter("*.txt"));
        source.setFilter(new AcceptOnceFileListFilter());
        return source;
    }


    /*
    @Bean(name = "consumer")
    public EventDrivenConsumer fileWritingConsumer(@Qualifier("fileInputChannel") SubscribableChannel inputChannel) {

        MessageHandler handler = new FileWritingMessageHandler(new File("data/source/outbound"));

        EventDrivenConsumer consumer = new EventDrivenConsumer(inputChannel, handler);
        return consumer;
    }


    /*
    @Bean
    @Transformer(inputChannel = "fileInputChannel", outputChannel = "processFileChannel")
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }
    */


    /*
    @Bean
    @ServiceActivator(inputChannel = "fileInputChannel")
    public MessageHandler fileWritingMessageHandler() {
//        Expression directoryExpression = new SpelExpressionParser().parseExpression("headers.directory");
//        FileWritingMessageHandler handler = new FileWritingMessageHandler(directoryExpression);
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("data/source/outbound"));
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setExpectReply(false);
        return handler;
    }


    /*
    @MessagingGateway(name = "myGateway", defaultRequestChannel = "fileInputChannel")
    public interface MyGateway {

        void writeToFile(@Header(FileHeaders.FILENAME) String fileName,
                         @Header(FileHeaders.FILENAME) File directory, String data);

    }
    */

}
