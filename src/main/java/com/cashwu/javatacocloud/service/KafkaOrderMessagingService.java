package com.cashwu.javatacocloud.service;

import com.cashwu.javatacocloud.model.TacoOrder;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @author cash.wu
 * @since 2024/05/23
 */
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
    private static final Logger log = LoggerFactory.getLogger(KafkaOrderMessagingService.class);
    private final KafkaTemplate<String, TacoOrder> kafkaTemplate;

    public KafkaOrderMessagingService(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {

        //        kafkaTemplate.send("tacocloud.order.queue",
        //                           order);

        order.setUser(null);

        kafkaTemplate.sendDefault(order);

        log.info("--> Sent order: {}",
                 order);
    }
}
