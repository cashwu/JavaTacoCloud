package com.cashwu.javatacocloud.service;

import com.cashwu.javatacocloud.model.TacoOrder;
import jakarta.jms.JMSException;
import org.springframework.stereotype.Service;

/**
 * @author cash.wu
 * @since 2024/05/23
 */
public interface OrderReceiver {

    TacoOrder receiveOrder() throws JMSException;
}
