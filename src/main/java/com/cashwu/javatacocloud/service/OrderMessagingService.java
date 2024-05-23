package com.cashwu.javatacocloud.service;

import com.cashwu.javatacocloud.model.TacoOrder;

import java.util.concurrent.ExecutionException;

/**
 * @author cash.wu
 * @since 2024/05/23
 */
public interface OrderMessagingService {

    void sendOrder(TacoOrder order);
}
