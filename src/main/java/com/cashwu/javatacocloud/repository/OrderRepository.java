package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.TacoOrder;

import java.util.Optional;

/**
 * @author cash.wu
 * @since 2024/05/15
 */
public interface OrderRepository {

    TacoOrder save(TacoOrder tacoOrder);

    Optional<TacoOrder> findById(Long id);
}
