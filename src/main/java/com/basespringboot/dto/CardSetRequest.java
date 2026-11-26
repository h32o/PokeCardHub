package com.basespringboot.dto;

import com.basespringboot.entity.CardSet;
import jakarta.validation.constraints.NotBlank;

public record CardSetRequest(
        @NotBlank String id,
        @NotBlank String seriesId,
        String tcgOnline,
        Integer cardCountOfficial,
        String releaseDate) {

    public CardSet toEntity() {
        CardSet set = new CardSet();
        set.setId(id);
        set.setTcgOnline(tcgOnline);
        set.setCardCountOfficial(cardCountOfficial);
        set.setReleaseDate(releaseDate);
        return set;
    }
}
