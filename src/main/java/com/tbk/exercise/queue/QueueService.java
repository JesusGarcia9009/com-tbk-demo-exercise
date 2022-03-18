package com.tbk.exercise.queue;

import java.util.Collections;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QueueService implements MessageListener {
    
    @Autowired
    private JmsTemplate jmsTemplate;

    private int counter = 0;

    public int completedJobs() {
        return counter;
    }

    public void send(String destination, String message) {
        log.info("sending message='{}' to destination='{}'", message, destination);
        jmsTemplate.convertAndSend(destination, message);
    }

    @SuppressWarnings("unchecked")
	public int pendingJobs(String queueName) {
        return jmsTemplate.browse(queueName, (s, qb) -> Collections.list(qb.getEnumeration()).size());
    }

    public boolean isUp() {
        var connection = jmsTemplate.getConnectionFactory();
        try {
            connection.createConnection().close();
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
            try {
                log.info("Processing task " + textMessage.getText());
                Thread.sleep(500);
                log.info("Completed task " + textMessage.getText());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            counter++;
        } else {
            log.error("Message is not a text message " + message.toString());
        }
    }
}
