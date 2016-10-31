package com.imarchuang.spring.integration.intertech.lab6.activator;

import com.imarchuang.spring.integration.intertech.domain.Shiporder;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * Created by mhuang on 9/9/2016.
 */
@Service
public class PrintingServiceActivator {

    @ServiceActivator(inputChannel="outboundChannel")
    public void printShiporder(Object order){
        System.out.println(order);
    }
}
