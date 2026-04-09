package com.translic.translic.services.impl;

import com.translic.translic.dto.HoraireRequest;
import com.translic.translic.entities.Horaire;
import com.translic.translic.entities.Ligne;
import com.translic.translic.exceptions.ResourceNotFoundException;
import com.translic.translic.repositories.HoraireRepository;
import com.translic.translic.repositories.LigneRepository;
import com.translic.translic.services.HoraireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HoraireServiceImpl implements HoraireService {

    private final HoraireRepository horaireRepository;
    private final LigneRepository ligneRepository;

    @Override
    public Horaire create(HoraireRequest request) {
        Ligne ligne = getLigneById(request.ligneId());
        Horaire horaire = new Horaire();
        horaire.setLigne(ligne);
        horaire.setHeureDepart(request.heureDepart());
        horaire.setHeureArrivee(request.heureArrivee());
        return horaireRepository.save(horaire);
    }

    @Override
    public List<Horaire> findAll() {
        return horaireRepository.findAll();
    }

    @Override
    public Horaire findById(Long id) {
        return horaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horaire not found with id: " + id));
    }

    @Override
    public Horaire update(Long id, HoraireRequest request) {
        Horaire existing = findById(id);
        Ligne ligne = getLigneById(request.ligneId());
        existing.setLigne(ligne);
        existing.setHeureDepart(request.heureDepart());
        existing.setHeureArrivee(request.heureArrivee());
        return horaireRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Horaire existing = findById(id);
        horaireRepository.delete(existing);
    }

    private Ligne getLigneById(Long ligneId) {
        return ligneRepository.findById(ligneId)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne not found with id: " + ligneId));
    }
}
