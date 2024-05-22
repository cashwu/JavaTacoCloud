package com.cashwu.javatacocloud.controller;

import com.cashwu.javatacocloud.model.Taco;
import com.cashwu.javatacocloud.repository.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
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
            return new ResponseEntity<>(optTaco.get(), OK);
        }

        return new ResponseEntity<>(null, NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
       return tacoRepository.save(taco);
    }
}
