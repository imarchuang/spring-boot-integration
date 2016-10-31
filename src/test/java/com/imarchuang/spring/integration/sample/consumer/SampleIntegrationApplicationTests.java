package com.imarchuang.spring.integration.sample.consumer;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.imarchuang.spring.integration.sample.SampleIntegrationApplication;
import com.imarchuang.spring.integration.sample.producer.ProducerApplication;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StreamUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mhuang on 8/19/2016.
 */
public class SampleIntegrationApplicationTests {

    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void start() throws Exception {
        context = SpringApplication.run(SampleIntegrationApplication.class);
    }

    @AfterClass
    public static void stop() {
        if (context != null) {
            context.close();
        }
    }

    @Before
    public void deleteOutput() {
        FileSystemUtils.deleteRecursively(new File("target/output"));
    }

    @Test
    public void testVanillaExchange() throws Exception {
        SpringApplication.run(ProducerApplication.class, "World");
        String output = getOutput();
        assertThat(output).contains("Hello World");
    }

    private String getOutput() throws Exception {
        Future<String> future = Executors.newSingleThreadExecutor()
                .submit(new Callable<String>() {
                    public String call() throws Exception {
                        Resource[] resources = getResourcesWithContent();
                        while (resources.length == 0) {
                            Thread.sleep(200);
                            resources = getResourcesWithContent();
                        }
                        StringBuilder builder = new StringBuilder();
                        for (Resource resource : resources) {
                            builder.append(new String(StreamUtils
                                    .copyToByteArray(resource.getInputStream())));
                        }
                        return builder.toString();
                    }
                });
        return future.get(30, TimeUnit.SECONDS);
    }

    private Resource[] getResourcesWithContent() throws IOException {
        Resource[] candidates = ResourcePatternUtils
                .getResourcePatternResolver(new DefaultResourceLoader())
                .getResources("file:target/output/**");
        for (Resource candidate : candidates) {
            if (candidate.contentLength() == 0) {
                return new Resource[0];
            }
        }
        return candidates;
    }
}