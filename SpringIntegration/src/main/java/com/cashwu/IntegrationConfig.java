package com.cashwu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

/**
 * @author cash.wu
 * @since 2024/05/24
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow myFlow() {

        return IntegrationFlow.from(inputChannel())
                              .<String, String>transform(String::toUpperCase)
                              .handle(System.out::println)
                              .get();

        //        return f -> f
        //                .<String, String>transform(String::toUpperCase)
        //                .handle(System.out::println);

        //        return IntegrationFlows
        //                .from(inputChannel())
        //                .<String, String>transform(String::toUpperCase)
        //                .handle(System.out::println)
        //                .get();
    }
}
