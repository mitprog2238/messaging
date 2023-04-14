package org.mitprog.jms.consumer;

import lombok.extern.java.Log;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;
import java.util.logging.Level;

@Component
@Log
public class JMSConsumer_Two {

    @JmsListener(destination = "Consumer.app2.VirtualTopic.medium")
    public void readMessage(final TextMessage message) {
        log.log(Level.INFO,"Received message by app_2 "+ message);
    }
}

