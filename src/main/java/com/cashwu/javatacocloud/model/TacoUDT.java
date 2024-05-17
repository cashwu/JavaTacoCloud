package com.cashwu.javatacocloud.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cash.wu
 * @since 2024/05/17
 */
@Data
@UserDefinedType("taco")
public class TacoUDT {

    private final String name;

    private final List<IngredientUDT> ingredients;
}
