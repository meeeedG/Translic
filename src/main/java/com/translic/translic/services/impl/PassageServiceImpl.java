package com.translic.translic.services.impl;

import com.translic.translic.dto.PassageRequest;
import com.translic.translic.entities.Horaire;
import com.translic.translic.entities.Passage;
import com.translic.translic.entities.Station;
import com.translic.translic.exceptions.ResourceNotFoundException;
import com.translic.translic.repositories.HoraireRepository;
import com.translic.translic.repositories.PassageRepository;
import com.translic.translic.repositories.StationRepository;
import com.translic.translic.services.PassageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassageServiceImpl implements PassageService {

    private final PassageRepository passageRepository;
    private final HoraireRepository horaireRepository;
    private final StationRepository stationRepository;

    @Override
    public Passage create(PassageRequest request) {
        Horaire horaire = getHoraireById(request.horaireId());
        Station station = getStationById(request.stationId());
        validateSameLigne(horaire, station);

        Passage passage = new Passage();
        passage.setHoraire(horaire);
        passage.setStation(station);
        passage.setOrdre(request.ordre());
        passage.setHeurePassage(request.heurePassage());
        return passageRepository.save(passage);
    }

    @Override
    public List<Passage> findAll() {
        return passageRepository.findAll();
    }

    @Override
    public List<Passage> findByHoraire(Long horaireId) {
        getHoraireById(horaireId);
        return passageRepository.findByHoraireIdOrderByOrdreAsc(horaireId);
    }

    @Override
    public Passage findById(Long id) {
        return passageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passage not found with id: " + id));
    }

    @Override
    public Passage update(Long id, PassageRequest request) {
        Passage existing = findById(id);
        Horaire horaire = getHoraireById(request.horaireId());
        Station station = getStationById(request.stationId());
        validateSameLigne(horaire, station);

        existing.setHoraire(horaire);
        existing.setStation(station);
        existing.setOrdre(request.ordre());
        existing.setHeurePassage(request.heurePassage());
        return passageRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Passage existing = findById(id);
        passageRepository.delete(existing);
    }

    private Horaire getHoraireById(Long id) {
        return horaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horaire not found with id: " + id));
    }

    private Station getStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Station not found with id: " + id));
    }

    private void validateSameLigne(Horaire horaire, Station station) {
        Long horaireLigneId = horaire.getLigne().getId();
        Long stationLigneId = station.getLigne().getId();
        if (!horaireLigneId.equals(stationLigneId)) {
            throw new IllegalArgumentException("Station and Horaire must belong to the same Ligne");
        }
    }
}
