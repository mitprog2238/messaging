package org.mitprog.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.JMSException;

@Configuration
@EnableJms
public class JMSConfig {
    /*@Bean
    public DefaultJmsListenerContainerFactory containerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setSessionTransacted(true);
        factory.setMaxMessagesPerTask(1);
        factory.setConcurrency("1-5");
        return factory;
    }*/

    @Bean
    public ActiveMQConnectionFactory senderConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");

        return activeMQConnectionFactory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public JmsTemplate jmsTemplateBro() {
        JmsTemplate jmsTemplate = new JmsTemplate(senderConnectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());

        return jmsTemplate;
    }
}