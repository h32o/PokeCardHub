package com.basespringboot.dto;

import com.basespringboot.entity.Series;
import jakarta.validation.constraints.NotBlank;

public record SeriesRequest(
        @NotBlank String id,
        String energies) {

    public Series toEntity() {
        Series series = new Series();
        series.setId(id);
        series.setEnergies(energies);
        return series;
    }
}
