package com.cashwu;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * @author cash.wu
 * @since 2024/05/24
 */
@MessagingGateway(defaultRequestChannel = "inputChannel")
public interface MyGateway {
    void send(String message);
}
