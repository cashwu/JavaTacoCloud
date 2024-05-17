package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author cash.wu
 * @since 2024/05/15
 */
public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {
}
