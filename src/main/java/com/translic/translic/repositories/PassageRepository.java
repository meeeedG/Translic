package com.translic.translic.repositories;

import com.translic.translic.entities.Passage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassageRepository extends JpaRepository<Passage, Long> {
    List<Passage> findByHoraireIdOrderByOrdreAsc(Long horaireId);
}
