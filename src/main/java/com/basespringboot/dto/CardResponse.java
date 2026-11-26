package com.basespringboot.dto;

import com.basespringboot.entity.Card;

public record CardResponse(
        String id,
        String setId,
        String localId,
        String category,
        String rarity,
        Integer hp,
        String stage,
        String filePath) {

    public static CardResponse from(Card card) {
        return new CardResponse(
                card.getId(),
                card.getCardSet().getId(), // PK access on the lazy proxy: no query triggered
                card.getLocalId(),
                card.getCategory(),
                card.getRarity(),
                card.getHp(),
                card.getStage(),
                card.getFilePath());
    }
}
