package com.translic.translic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StationRequest(
        @NotBlank @Size(max = 120) String nom,
        @NotBlank @Size(max = 255) String adresse,
        @NotNull Long ligneId
) {
}
