package com.cashwu.javatacocloud.listener;

import com.cashwu.javatacocloud.model.TacoOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author cash.wu
 * @since 2024/05/23
 */

@Component
public class OrderListener {

    private static final Logger log = LoggerFactory.getLogger(OrderListener.class);

//    @JmsListener(destination = "tacocloud.order.queue")
//    public void onOrderReceived(TacoOrder order) {
//
//        log.info("<-- Order received: {}",
//                 order);
//    }

    @KafkaListener(topics = "tacocloud.order.queue", groupId = "group1")
    public void handle(TacoOrder tacoOrder) {

        log.info("<-- Received order: {}", tacoOrder);
    }
}
