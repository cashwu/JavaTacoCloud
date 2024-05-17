package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.Ingredient;
import com.cashwu.javatacocloud.model.IngredientUDT;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author cash.wu
 * @since 2024/05/17
 */
@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepository;

    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) {
            return null;
        }

        return ingredient.map(i -> {
                             return new IngredientUDT(i.getName(), i.getType());
                         })
                         .get();
    }

}
