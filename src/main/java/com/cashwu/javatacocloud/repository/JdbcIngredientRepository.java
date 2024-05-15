package com.cashwu.javatacocloud.repository;

import com.cashwu.javatacocloud.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author cash.wu
 * @since 2024/05/15
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from ingredient", this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {

        List<Ingredient> results = jdbcTemplate.query("select id, name, type from ingredient where id = ?",
                                                      this::mapRowToIngredient,
                                                      id);

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {

        jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                            ingredient.getId(),
                            ingredient.getName(),
                            ingredient.getType());

        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet,
                                          int rowNum) throws SQLException {

        return new Ingredient(resultSet.getString("id"),
                              resultSet.getString("name"),
                              Ingredient.Type.valueOf(resultSet.getString("type")));
    }
}
