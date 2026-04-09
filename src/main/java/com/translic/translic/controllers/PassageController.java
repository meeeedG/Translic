package com.translic.translic.controllers;

import com.translic.translic.dto.PassageRequest;
import com.translic.translic.entities.Passage;
import com.translic.translic.services.PassageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passages")
@RequiredArgsConstructor
public class PassageController {

    private final PassageService passageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Passage create(@Valid @RequestBody PassageRequest request) {
        return passageService.create(request);
    }

    @GetMapping
    public List<Passage> findAll() {
        return passageService.findAll();
    }

    @GetMapping("/horaire/{horaireId}")
    public List<Passage> findByHoraire(@PathVariable Long horaireId) {
        return passageService.findByHoraire(horaireId);
    }

    @GetMapping("/{id}")
    public Passage findById(@PathVariable Long id) {
        return passageService.findById(id);
    }

    @PutMapping("/{id}")
    public Passage update(@PathVariable Long id, @Valid @RequestBody PassageRequest request) {
        return passageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        passageService.delete(id);
    }
}
