package com.imarchuang.spring.integration.intertech.lab5.activator;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by mhuang on 9/9/2016.
 */
@Service("serviceActivator")
public class ExampleServiceActivator {

    @ServiceActivator(inputChannel="norwaySAChannel")
    public void printShiporder(Object order){
        System.out.println("###FROM ExampleServiceActivator:: " + order);
    }
}
