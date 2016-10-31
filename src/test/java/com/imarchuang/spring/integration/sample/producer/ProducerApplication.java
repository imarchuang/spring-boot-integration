package com.imarchuang.spring.integration.sample.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by mhuang on 8/19/2016.
 */
@Configuration
public class ProducerApplication implements CommandLineRunner {
    public void run(String... args) throws Exception {
        new File("target/input").mkdirs();
        if (args.length > 0) {
            FileOutputStream stream = new FileOutputStream(
                    "target/input/data" + System.currentTimeMillis() + ".txt");
            for (String arg : args) {
                stream.write(arg.getBytes());
            }
            stream.flush();
            stream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
