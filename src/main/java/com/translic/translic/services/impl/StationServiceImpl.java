package com.translic.translic.services.impl;

import com.translic.translic.dto.StationRequest;
import com.translic.translic.entities.Ligne;
import com.translic.translic.entities.Station;
import com.translic.translic.exceptions.ResourceNotFoundException;
import com.translic.translic.repositories.LigneRepository;
import com.translic.translic.repositories.StationRepository;
import com.translic.translic.services.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final LigneRepository ligneRepository;

    @Override
    public Station create(StationRequest request) {
        Ligne ligne = getLigneById(request.ligneId());
        Station station = new Station();
        station.setNom(request.nom());
        station.setAdresse(request.adresse());
        station.setLigne(ligne);
        return stationRepository.save(station);
    }

    @Override
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Station findById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + id));
    }

    @Override
    public Station update(Long id, StationRequest request) {
        Station existing = findById(id);
        Ligne ligne = getLigneById(request.ligneId());
        existing.setNom(request.nom());
        existing.setAdresse(request.adresse());
        existing.setLigne(ligne);
        return stationRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Station existing = findById(id);
        stationRepository.delete(existing);
    }

    private Ligne getLigneById(Long ligneId) {
        return ligneRepository.findById(ligneId)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne not found with id: " + ligneId));
    }
}
