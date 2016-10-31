package com.imarchuang.spring.integration.intertech.lab6.config;

import org.springframework.context.annotation.*;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.xml.transformer.MarshallingTransformer;
import org.springframework.integration.xml.transformer.ResultToStringTransformer;
import org.springframework.integration.xml.transformer.ResultTransformer;
import org.springframework.integration.xml.transformer.UnmarshallingTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhuang on 9/9/2016.
 */
@Configuration
@ComponentScan("com.imarchuang.spring.integration.intertech.lab6")	//@Component
@IntegrationComponentScan("com.imarchuang.spring.integration.intertech.lab6")	//@MessagingGateway
@EnableIntegration
@ImportResource("classpath:/META-INF/si-components_lab6.xml")
public class InfrastructureConfiguration {

    @Bean(name = "inboundChannel")
    @Description("A direct channel.")
    public SubscribableChannel inboundChannel() {
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

    @Bean(name = "shipOrder-Channel")
    @Description("A direct channel.")
    public SubscribableChannel shipOrderChannel() {
        return new DirectChannel();
    }

    @Bean(name = "total-price-enricher-channel")
    @Description("A direct channel.")
    public SubscribableChannel totalPriceEnricherChannel() {
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

    @Bean(name = "xml-2-object-transformer")
    @Transformer(inputChannel = "xml-inboundChannel", outputChannel = "shipOrder-Channel")
    public UnmarshallingTransformer unmarshallingTransformer(Jaxb2Marshaller jaxb2Marshaller) {
        return new UnmarshallingTransformer(jaxb2Marshaller);
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.imarchuang.spring.integration.intertech.domain");

        return jaxb2Marshaller;
    }


}
