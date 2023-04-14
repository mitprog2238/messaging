package org.mitprog.jms;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        jmsTemplate.convertAndSend(new ActiveMQTopic("VirtualTopic.medium"), "123 Test virtual topic");
        jmsTemplate.convertAndSend(new ActiveMQTopic("VirtualTopic.medium"), "321 Test virtual topic");
        jmsTemplate.convertAndSend(new ActiveMQTopic("VirtualTopic.medium"), "357 Test virtual topic");
    }
}
