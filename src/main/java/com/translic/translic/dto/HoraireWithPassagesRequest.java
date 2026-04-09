package com.translic.translic.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

public record HoraireWithPassagesRequest(
        @NotNull Long ligneId,
        @NotNull LocalTime heureDepart,
        @NotNull LocalTime heureArrivee,
        @NotEmpty List<@Valid HorairePassageItemRequest> passages
) {
}
