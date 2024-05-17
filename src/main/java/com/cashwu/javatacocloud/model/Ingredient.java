package com.cashwu.javatacocloud.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author cash.wu
 * @since 2024/05/14
 */
@Data
@AllArgsConstructor
@Document("ingredients")
@NoArgsConstructor(access = AccessLevel.PRIVATE,
                   force = true)
public class Ingredient {

    @Id
    private String id;

    private String name;
    private Type type;


    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
