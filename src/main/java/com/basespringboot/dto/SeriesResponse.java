package com.basespringboot.dto;

import com.basespringboot.entity.Series;

public record SeriesResponse(String id, String energies) {

    public static SeriesResponse from(Series series) {
        return new SeriesResponse(series.getId(), series.getEnergies());
    }
}
