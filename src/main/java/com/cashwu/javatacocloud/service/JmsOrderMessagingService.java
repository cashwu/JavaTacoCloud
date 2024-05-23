package com.cashwu.javatacocloud.service;

import com.cashwu.javatacocloud.model.TacoOrder;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

/**
 * @author cash.wu
 * @since 2024/05/23
 */
//@Service
//public class JmsOrderMessagingService implements OrderMessagingService {
//
//    private static final Logger log = LoggerFactory.getLogger(JmsOrderMessagingService.class);
//    private final JmsTemplate jmsTemplate;
//    private final Destination destination;
//
//    public JmsOrderMessagingService(JmsTemplate jmsTemplate,
//                                    Destination destination) {
//        this.jmsTemplate = jmsTemplate;
//        this.destination = destination;
//    }
//
//    @Override
//    public void sendOrder(TacoOrder order) {
//        //        jmsTemplate.send(session -> session.createObjectMessage(order));
//        //
//        //        jmsTemplate.send(destination,
//        //                         session -> session.createObjectMessage(order));
//
//        order.setUser(null);
//
//        jmsTemplate.convertAndSend("tacocloud.order.queue",
//                                   order,
//                                   this::addOrderSource);
//
//        log.info("---> Sent order: {}", order);
//    }
//
//    private Message addOrderSource(Message message) throws JMSException {
//        message.setStringProperty("X_ORDER_SOURCE",
//                                  "WEB");
//        return message;
//    }
//}
//
//
