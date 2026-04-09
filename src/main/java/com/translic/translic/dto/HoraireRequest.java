package com.translic.translic.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HoraireRequest(
        @NotNull Long ligneId,
        @NotNull LocalTime heureDepart,
        @NotNull LocalTime heureArrivee
) {
}
