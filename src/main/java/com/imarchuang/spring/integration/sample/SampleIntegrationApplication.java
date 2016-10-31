package com.imarchuang.spring.integration.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.Consumer;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

import java.io.File;

/**
 * Created by mhuang on 8/19/2016.
 */
@SpringBootApplication
@EnableConfigurationProperties(ServiceProperties.class)
public class SampleIntegrationApplication {
    @Bean
    public FileReadingMessageSource fileReader() {
        FileReadingMessageSource reader = new FileReadingMessageSource();
        reader.setDirectory(new File("target/input"));
        return reader;
    }

    @Bean
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler writer = new FileWritingMessageHandler(
                new File("target/output"));
        writer.setExpectReply(false);
        return writer;
    }

    @Bean
    public IntegrationFlow integrationFlow(SampleEndpoint endpoint) {
        return IntegrationFlows.from(fileReader(), new FixedRatePoller())
                .channel(inputChannel()).handle(endpoint).channel(outputChannel())
                .handle(fileWriter()).get();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleIntegrationApplication.class, args);
    }

    private static class FixedRatePoller
            implements Consumer<SourcePollingChannelAdapterSpec> {

        public void accept(SourcePollingChannelAdapterSpec spec) {
            spec.poller(Pollers.fixedRate(500));
        }

    }

}
