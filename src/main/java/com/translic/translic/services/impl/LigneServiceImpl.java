package com.translic.translic.services.impl;

import com.translic.translic.dto.LigneRequest;
import com.translic.translic.entities.Ligne;
import com.translic.translic.exceptions.ResourceNotFoundException;
import com.translic.translic.repositories.LigneRepository;
import com.translic.translic.services.LigneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LigneServiceImpl implements LigneService {

    private final LigneRepository ligneRepository;

    @Override
    public Ligne create(LigneRequest request) {
        Ligne ligne = new Ligne();
        ligne.setNom(request.nom());
        ligne.setType(request.type());
        return ligneRepository.save(ligne);
    }

    @Override
    public List<Ligne> findAll() {
        return ligneRepository.findAll();
    }

    @Override
    public Ligne findById(Long id) {
        return ligneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne not found with id: " + id));
    }

    @Override
    public Ligne update(Long id, LigneRequest request) {
        Ligne existing = findById(id);
        existing.setNom(request.nom());
        existing.setType(request.type());
        return ligneRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Ligne existing = findById(id);
        ligneRepository.delete(existing);
    }
}
