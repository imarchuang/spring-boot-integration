package com.imarchuang.spring.integration.intertech.lab8;

import com.imarchuang.spring.integration.intertech.lab8.config.InfrastructureConfiguration;
import com.imarchuang.spring.integration.intertech.lab8.service.PigLatinService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by mhuang on 8/19/2016.
 */
@SpringBootApplication
public class GatewayDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayDemoApplication.class, args);

        testGateway(context);
    }

    private static void testGateway(ConfigurableApplicationContext context) {

        // MessageChannel channel = context.getBean("requestChannel",
        // MessageChannel.class);
        // Message<String> message =
        // MessageBuilder.withPayload("Hello brave new world").build();
        // channel.send(message);

        InfrastructureConfiguration.PigLatinServiceGateway service =
                context.getBean("pigLatinServiceGateway", InfrastructureConfiguration.PigLatinServiceGateway.class);
        System.out.println(service.translate("Hello brave new world"));
    }

}
