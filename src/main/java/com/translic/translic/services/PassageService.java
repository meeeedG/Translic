package com.translic.translic.services;

import com.translic.translic.dto.PassageRequest;
import com.translic.translic.entities.Passage;

import java.util.List;

public interface PassageService {
    Passage create(PassageRequest request);

    List<Passage> findAll();

    List<Passage> findByHoraire(Long horaireId);

    Passage findById(Long id);

    Passage update(Long id, PassageRequest request);

    void delete(Long id);
}
