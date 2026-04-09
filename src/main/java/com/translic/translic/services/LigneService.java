package com.translic.translic.services;

import com.translic.translic.dto.LigneRequest;
import com.translic.translic.entities.Ligne;

import java.util.List;

public interface LigneService {
    Ligne create(LigneRequest request);

    List<Ligne> findAll();

    Ligne findById(Long id);

    Ligne update(Long id, LigneRequest request);

    void delete(Long id);
}
