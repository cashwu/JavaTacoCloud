package com.cashwu.javatacocloud.controller;

import com.cashwu.javatacocloud.model.Ingredient;
import com.cashwu.javatacocloud.model.Ingredient.Type;
import com.cashwu.javatacocloud.model.MyUser;
import com.cashwu.javatacocloud.model.Taco;
import com.cashwu.javatacocloud.model.TacoOrder;
import com.cashwu.javatacocloud.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        //        List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        //                                                     new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        //                                                     new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        //                                                     new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        //                                                     new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        //                                                     new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        //                                                     new Ingredient("CHED", "Cheddar", Type.CHEESE),
        //                                                     new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        //                                                     new Ingredient("SLSA", "Salsa", Type.SAUCE),
        //                                                     new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString()
                                   .toLowerCase(), filterByType(ingredients, type));
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
    public String showDesignForm(Authentication authentication) {

        MyUser user = (MyUser) authentication.getPrincipal();

        System.out.println("username -- " + user.getUsername());

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

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients,
                                              Type type) {

        return StreamSupport.stream(ingredients.spliterator(), false)
                            .toList()
                            .stream()
                            .filter(x -> x.getType()
                                          .equals(type))
                            .collect(Collectors.toList());

//        return ingredients.stream()
//                          .filter(x -> x.getType()
//                                        .equals(type))
//                          .collect(Collectors.toList());
    }

}
