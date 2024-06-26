package com.cashwu.javatacocloud.controller;

import com.cashwu.javatacocloud.model.Ingredient;
import com.cashwu.javatacocloud.model.Ingredient.Type;
import com.cashwu.javatacocloud.model.Taco;
import com.cashwu.javatacocloud.model.TacoOrder;
import com.cashwu.javatacocloud.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author cash.wu
 * @since 2024/05/14
 */
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                               filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    //  @PostMapping
    //  public String processTaco(Taco taco,
    //  			@ModelAttribute TacoOrder tacoOrder) {
    //    tacoOrder.addTaco(taco);
    //    log.info("Processing taco: {}", taco);
    //
    //    return "redirect:/orders/current";
    //  }

    @PostMapping
    public String processTaco(@Valid Taco taco,
                              Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients,
                                              Type type) {

        return ingredients.stream()
                          .filter(x -> x.getType()
                                        .equals(type))
                          .collect(Collectors.toList());
    }

}
