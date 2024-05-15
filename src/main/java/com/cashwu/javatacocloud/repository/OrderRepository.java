package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author cash.wu
 * @since 2024/05/15
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

//    TacoOrder save(TacoOrder tacoOrder);
//
//    Optional<TacoOrder> findById(Long id);
}
