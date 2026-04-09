package com.translic.translic.controllers;

import com.translic.translic.dto.LigneRequest;
import com.translic.translic.entities.Ligne;
import com.translic.translic.services.LigneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lignes")
@RequiredArgsConstructor
public class LigneController {

    private final LigneService ligneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ligne create(@Valid @RequestBody LigneRequest request) {
        return ligneService.create(request);
    }

    @GetMapping
    public List<Ligne> findAll() {
        return ligneService.findAll();
    }

    @GetMapping("/{id}")
    public Ligne findById(@PathVariable Long id) {
        return ligneService.findById(id);
    }

    @PutMapping("/{id}")
    public Ligne update(@PathVariable Long id, @Valid @RequestBody LigneRequest request) {
        return ligneService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ligneService.delete(id);
    }
}
