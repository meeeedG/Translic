package com.translic.translic.services;

import com.translic.translic.dto.StationRequest;
import com.translic.translic.entities.Station;

import java.util.List;

public interface StationService {
    Station create(StationRequest request);

    List<Station> findAll();

    Station findById(Long id);

    Station update(Long id, StationRequest request);

    void delete(Long id);
}
