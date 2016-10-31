package com.imarchuang.spring.integration.intertech.lab3.selector;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by mhuang on 9/9/2016.
 */
@Component("selector")
public class FileSelector implements MessageSelector {

    public boolean accept(Message<?> message) {
        if (message.getPayload() instanceof File
                && ((File) message.getPayload()).getName().startsWith("msg")) {
            return false;
        }
        return true;
    }
}
