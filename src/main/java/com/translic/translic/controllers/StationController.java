package com.translic.translic.controllers;

import com.translic.translic.dto.StationRequest;
import com.translic.translic.entities.Station;
import com.translic.translic.services.StationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Station create(@Valid @RequestBody StationRequest request) {
        return stationService.create(request);
    }

    @GetMapping
    public List<Station> findAll() {
        return stationService.findAll();
    }

    @GetMapping("/{id}")
    public Station findById(@PathVariable Long id) {
        return stationService.findById(id);
    }

    @PutMapping("/{id}")
    public Station update(@PathVariable Long id, @Valid @RequestBody StationRequest request) {
        return stationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        stationService.delete(id);
    }
}
