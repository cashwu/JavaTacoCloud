package com.cashwu.javatacocloud.controller;

import com.cashwu.javatacocloud.model.Ingredient;
import com.cashwu.javatacocloud.model.Taco;
import com.cashwu.javatacocloud.repository.TacoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * @author cash.wu
 * @since 2024/05/22
 */
@RestController
@RequestMapping(path = "/api/tacos",
                produces = "application/json")
//@CrossOrigin(origins = "http://")
public class TacoController {

    private static final Logger log = LoggerFactory.getLogger(TacoController.class);
    private final TacoRepository tacoRepository;
    private final RestTemplate restTemplate;

    public TacoController(TacoRepository tacoRepository,
                          RestTemplate restTemplate) {
        this.tacoRepository = tacoRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {

        PageRequest pageRequest = PageRequest.of(0,
                                                 12,
                                                 Sort.by("createdAt")
                                                     .descending());

        return tacoRepository.findAll(pageRequest)
                             .getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> findById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);

        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(),
                                        OK);
        }

        return new ResponseEntity<>(null,
                                    NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @GetMapping("/ingredient/{id}")
    public Ingredient getIngredientById(@PathVariable String id) {

        // 01
        //        return restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}",
        //                                         Ingredient.class,
        //                                         id);

        // 02
        //        HashMap<String, String> urlVariables = new HashMap<>();
        //        urlVariables.put("id", id);
        //
        //        return restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}",
        //                                         Ingredient.class,
        //                                         urlVariables);

        // 03
//        HashMap<String, String> urlVariables = new HashMap<>();
//        urlVariables.put("id",
//                         id);
//
//        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/data-api/ingredients/{id}")
//                                      .build(urlVariables);
//
//        return restTemplate.getForObject(uri,
//                                         Ingredient.class);

        ResponseEntity<Ingredient> responseEntity = restTemplate.getForEntity("http://localhost:8080/data-api/ingredients/{id}",
                                                                         Ingredient.class,
                                                                         id);
        log.info("fetch -- {}",
                 responseEntity.getHeaders()
                               .getDate());

        return responseEntity.getBody();

    }
}
