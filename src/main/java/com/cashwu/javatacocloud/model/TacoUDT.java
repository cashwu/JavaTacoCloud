package com.cashwu.javatacocloud.model;

import lombok.Data;

import java.util.List;

/**
 * @author cash.wu
 * @since 2024/05/17
 */
@Data
public class TacoUDT {

    private final String name;

    private final List<IngredientUDT> ingredients;
}
