package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author cash.wu
 * @since 2024/05/15
 */
public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}


