package com.imarchuang.spring.integration.amqp.receiver;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mhuang on 9/12/2016.
 */
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
