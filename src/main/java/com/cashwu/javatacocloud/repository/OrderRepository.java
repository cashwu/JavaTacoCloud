package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author cash.wu
 * @since 2024/05/15
 */
public interface OrderRepository extends CrudRepository<TacoOrder, String> {
}
