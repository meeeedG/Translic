package com.translic.translic.services.impl;

import com.translic.translic.dto.HoraireRequest;
import com.translic.translic.dto.HoraireWithPassagesRequest;
import com.translic.translic.entities.Horaire;
import com.translic.translic.entities.Ligne;
import com.translic.translic.entities.Passage;
import com.translic.translic.entities.Station;
import com.translic.translic.exceptions.ResourceNotFoundException;
import com.translic.translic.repositories.HoraireRepository;
import com.translic.translic.repositories.LigneRepository;
import com.translic.translic.repositories.PassageRepository;
import com.translic.translic.repositories.StationRepository;
import com.translic.translic.services.HoraireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HoraireServiceImpl implements HoraireService {

    private final HoraireRepository horaireRepository;
    private final LigneRepository ligneRepository;
    private final StationRepository stationRepository;
    private final PassageRepository passageRepository;

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
    @Transactional
    public Horaire createWithPassages(HoraireWithPassagesRequest request) {
        Ligne ligne = getLigneById(request.ligneId());
        validateUniqueOrdre(request.passages().stream().map(item -> item.ordre()).toList());
        Horaire horaire = new Horaire();
        horaire.setLigne(ligne);
        horaire.setHeureDepart(request.heureDepart());
        horaire.setHeureArrivee(request.heureArrivee());
        Horaire savedHoraire = horaireRepository.save(horaire);

        for (var item : request.passages()) {
            Station station = stationRepository.findById(item.stationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + item.stationId()));
            if (!station.getLigne().getId().equals(ligne.getId())) {
                throw new IllegalArgumentException("All stations must belong to the same Ligne as the Horaire");
            }
            Passage passage = new Passage();
            passage.setHoraire(savedHoraire);
            passage.setStation(station);
            passage.setOrdre(item.ordre());
            passage.setHeurePassage(item.heurePassage());
            passageRepository.save(passage);
        }
        return horaireRepository.findById(savedHoraire.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Horaire not found with id: " + savedHoraire.getId()));
    }

    private void validateUniqueOrdre(List<Integer> ordres) {
        Set<Integer> seen = new HashSet<>();
        for (Integer ordre : ordres) {
            if (!seen.add(ordre)) {
                throw new IllegalArgumentException("Each passage ordre must be unique for a Horaire");
            }
        }
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
