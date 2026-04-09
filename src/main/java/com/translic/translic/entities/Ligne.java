package com.translic.translic.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lignes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ligne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nom;

    @Column(nullable = false, length = 60)
    private String type;

    @JsonManagedReference
    @OneToMany(mappedBy = "ligne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Station> stations = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "ligne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Horaire> horaires = new ArrayList<>();
}
