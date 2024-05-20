package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.MyUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author cash.wu
 * @since 2024/05/20
 */
public interface UserRepository extends CrudRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
