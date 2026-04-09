package com.translic.translic.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record HorairePassageItemRequest(
        @NotNull Long stationId,
        @NotNull @Min(1) Integer ordre,
        LocalTime heurePassage
) {
}
