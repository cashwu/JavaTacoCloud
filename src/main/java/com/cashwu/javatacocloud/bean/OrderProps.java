package com.cashwu.javatacocloud.bean;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cash.wu
 * @since 2024/05/21
 */
@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class OrderProps {

    @Min(value = 5)
    @Max(value = 25)
    private int PageSize = 20;
}
