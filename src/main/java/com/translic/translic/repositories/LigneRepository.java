package com.translic.translic.repositories;

import com.translic.translic.entities.Ligne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneRepository extends JpaRepository<Ligne, Long> {
}
