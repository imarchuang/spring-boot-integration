package com.imarchuang.spring.integration.intertech.lab7.config;

import org.springframework.context.annotation.*;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.integration.xml.transformer.MarshallingTransformer;
import org.springframework.integration.xml.transformer.ResultToStringTransformer;
import org.springframework.integration.xml.transformer.ResultTransformer;
import org.springframework.integration.xml.transformer.UnmarshallingTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhuang on 9/9/2016.
 */
@Configuration
@ComponentScan("com.imarchuang.spring.integration.intertech.lab7")	//@Component
@IntegrationComponentScan("com.imarchuang.spring.integration.intertech.lab7")	//@MessagingGateway
@EnableIntegration
//@ImportResource("classpath:/META-INF/si-components_lab7.xml")
public class InfrastructureConfiguration {

    @Bean(name = "inboundChannel")
    @Description("A direct channel.")
    public SubscribableChannel directChannel() {
        return new DirectChannel();
    }

    @Bean(name = "xml-inboundChannel")
    @Description("A direct channel.")
    public SubscribableChannel inboundXMLChannel() {
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

    @Bean
    @Description("A direct channel.")
    public SubscribableChannel norwayChannel() {
        return new DirectChannel();
    }
    @Bean
    @Description("A direct channel.")
    public SubscribableChannel usaChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("A direct channel.")
    public SubscribableChannel norwayFileChannel() {
        return new DirectChannel();
    }
    @Bean
    @Description("A direct channel.")
    public SubscribableChannel norwaySAChannel() {
        return new DirectChannel();
    }

    @Bean(name = "revenueProcessingChannel")
    @Description("A direct channel.")
    public SubscribableChannel revenueProcessingChannel() {
        return new DirectChannel();
    }

    @Bean(name = "xml-outboundChannel")
    @Description("A direct channel.")
    public SubscribableChannel xmlOutboundChannel() {
        return new DirectChannel();
    }

    @Bean(name = "string-outboundChannel")
    @Description("A direct channel.")
    public SubscribableChannel stringOutboundChannel() {
        return new DirectChannel();
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
        source.setDirectory(new File("data/source/inboundXML"));
        source.setFilter(new SimplePatternFileListFilter("*.xml"));
        source.setFilter(new AcceptOnceFileListFilter());
        return source;
    }

    @Bean(name = "file-2-string-transformer")
    @Transformer(inputChannel = "inboundChannel", outputChannel = "xml-inboundChannel")
    public FileToStringTransformer fileToStringTransformer() {
        FileToStringTransformer transformer = new FileToStringTransformer();
        transformer.setCharset("UTF-8");
        return transformer;
    }

    @Bean(name = "xml-to-string-transformer")
    @Transformer(inputChannel = "xml-outboundChannel", outputChannel = "string-outboundChannel")
    public ObjectToStringTransformer objectToStringTransformer() {
        return new ObjectToStringTransformer();
    }

    @Bean(name = "object-2-xml-transformer")
    @Transformer(inputChannel = "outboundChannel", outputChannel = "xml-outboundChannel")
    public MarshallingTransformer marshallingTransformer(Jaxb2Marshaller jaxb2Marshaller) {

        MarshallingTransformer marshallingTransformer = null;
        ResultTransformer resultTransformer = new ResultToStringTransformer();
        try {
            marshallingTransformer = new MarshallingTransformer(jaxb2Marshaller, resultTransformer);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return marshallingTransformer;

    }

    @Bean(name = "xml-2-object-transformer")
    @Transformer(inputChannel = "xml-inboundChannel", outputChannel = "revenueProcessingChannel")
    public UnmarshallingTransformer unmarshallingTransformer(Jaxb2Marshaller jaxb2Marshaller) {
        return new UnmarshallingTransformer(jaxb2Marshaller);
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.imarchuang.spring.integration.intertech.domain");

        return jaxb2Marshaller;
    }

    @Bean
    @ServiceActivator(inputChannel = "string-outboundChannel")
    public MessageHandler fileWritingMessageHandler() {
//        Expression directoryExpression = new SpelExpressionParser().parseExpression("headers.directory");
//        FileWritingMessageHandler handler = new FileWritingMessageHandler(directoryExpression);
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("data/source/outboundXML"));
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setExpectReply(false);
        handler.setAutoCreateDirectory(true);
        //handler.afterPropertiesSet();
        return handler;
    }

    @MessagingGateway(defaultRequestChannel = "string-outboundChannel")
    public interface MyGateway {

        void writeToFile(String fileName, File directory, String data);

    }

}
