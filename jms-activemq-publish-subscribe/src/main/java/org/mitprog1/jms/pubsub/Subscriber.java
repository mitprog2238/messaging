package org.mitprog1.jms.pubsub;

import lombok.extern.java.Log;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Level;

@Log
public class Subscriber {

    private static final String NO_GREETING = "no greeting";

    private String clientId;
    private Connection connection;
    private MessageConsumer messageConsumer;

    public void create(String clientId, String topicName) throws JMSException {
        this.clientId = clientId;

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

        connection = connectionFactory.createConnection();
        connection.setClientID(clientId);

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // create the Topic from which messages will be received
        Topic topic = session.createTopic(topicName);

        // create a MessageConsumer for receiving messages
        messageConsumer = session.createConsumer(topic);

        // start the connection in order to receive messages
        connection.start();
    }

    public String getGreeting(int timeout) throws JMSException {

        String greeting = NO_GREETING;

        // read a message from the topic destination
        Message message = messageConsumer.receive(timeout);

        // check if a message was received
        if (message != null) {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            log.log(Level.INFO,clientId + ": received message with text= "+ text);
            greeting = "Hello " + text + "!";

        } else {
            log.log(Level.INFO,clientId + ": no message received");
        }

        log.log(Level.INFO,"greeting= "+ greeting);
        return greeting;
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }
}
