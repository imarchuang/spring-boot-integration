package com.imarchuang.spring.integration.intertech.lab3;

import com.imarchuang.spring.integration.intertech.lab3.selector.FileSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by mhuang on 9/9/2016.
 */
@Component
public class CustomFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileSelector selector;

    @Filter(inputChannel="inboundChannel", outputChannel="outboundChannel")
    public boolean filterCourse(Message message) {
        return selector.accept(message);
    }
}
