package com.translic.translic.services;

import com.translic.translic.dto.HoraireRequest;
import com.translic.translic.dto.HoraireWithPassagesRequest;
import com.translic.translic.entities.Horaire;

import java.util.List;

public interface HoraireService {
    Horaire create(HoraireRequest request);

    Horaire createWithPassages(HoraireWithPassagesRequest request);

    List<Horaire> findAll();

    Horaire findById(Long id);

    Horaire update(Long id, HoraireRequest request);

    void delete(Long id);
}
