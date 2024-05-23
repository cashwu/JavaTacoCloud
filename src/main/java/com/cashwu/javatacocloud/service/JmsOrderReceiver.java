package com.cashwu.javatacocloud.service;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.cashwu.javatacocloud.model.TacoOrder;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

/**
 * @author cash.wu
 * @since 2024/05/23
 */
@Component
public class JmsOrderReceiver implements OrderReceiver {

    private final JmsTemplate jmsTemplate;

    public JmsOrderReceiver(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public TacoOrder receiveOrder() {

        return (TacoOrder) jmsTemplate.receiveAndConvert("tacocloud.order.queue");
    }
}
