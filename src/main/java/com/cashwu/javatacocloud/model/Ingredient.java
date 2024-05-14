package com.cashwu.javatacocloud.model;

import lombok.Data;

/**
 * @author cash.wu
 * @since 2024/05/14
 */
@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;


    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
