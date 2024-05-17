package com.cashwu.javatacocloud.model;

import lombok.*;

/**
 * @author cash.wu
 * @since 2024/05/17
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE,
                   force = true)
public class IngredientUDT {

    private final String name;
    private final Ingredient.Type type;
}
