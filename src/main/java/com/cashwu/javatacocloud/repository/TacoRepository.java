package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cash.wu
 * @since 2024/05/22
 */
@Repository
public interface TacoRepository extends JpaRepository<Taco, Long> {
}
