package com.basespringboot.dto;

import com.basespringboot.entity.CardSet;

public record CardSetResponse(
        String id,
        String seriesId,
        String tcgOnline,
        Integer cardCountOfficial,
        String releaseDate) {

    public static CardSetResponse from(CardSet set) {
        // set.getSeries().getId() is safe on a lazy proxy: reading the id (primary key)
        // does not trigger initialization.
        return new CardSetResponse(
                set.getId(),
                set.getSeries().getId(),
                set.getTcgOnline(),
                set.getCardCountOfficial(),
                set.getReleaseDate());
    }
}
