package com.translic.translic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LigneRequest(
        @NotBlank @Size(max = 120) String nom,
        @NotBlank @Size(max = 60) String type
) {
}
