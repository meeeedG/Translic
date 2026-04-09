package com.translic.translic.controllers;

import com.translic.translic.dto.HoraireRequest;
import com.translic.translic.dto.HoraireWithPassagesRequest;
import com.translic.translic.entities.Horaire;
import com.translic.translic.services.HoraireService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horaires")
@RequiredArgsConstructor
public class HoraireController {

    private final HoraireService horaireService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Horaire create(@Valid @RequestBody HoraireRequest request) {
        return horaireService.create(request);
    }

    @PostMapping("/with-passages")
    @ResponseStatus(HttpStatus.CREATED)
    public Horaire createWithPassages(@Valid @RequestBody HoraireWithPassagesRequest request) {
        return horaireService.createWithPassages(request);
    }

    @GetMapping
    public List<Horaire> findAll() {
        return horaireService.findAll();
    }

    @GetMapping("/{id}")
    public Horaire findById(@PathVariable Long id) {
        return horaireService.findById(id);
    }

    @PutMapping("/{id}")
    public Horaire update(@PathVariable Long id, @Valid @RequestBody HoraireRequest request) {
        return horaireService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        horaireService.delete(id);
    }
}
