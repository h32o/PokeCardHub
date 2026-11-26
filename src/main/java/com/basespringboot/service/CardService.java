package com.basespringboot.service;

import java.util.List;
import java.util.Optional;

import com.basespringboot.dto.CardRequest;
import com.basespringboot.entity.Card;

public interface CardService {

    Optional<Card> create(CardRequest request);

    Optional<Card> getById(String id);

    List<Card> findAll();

    Optional<Card> update(String id, CardRequest request);

    boolean delete(String id);

    /**
     * Business rules for a card payload.
     * @param selfId the card id being updated, or null on create.
     */
    boolean isValid(CardRequest request, String selfId);
}
