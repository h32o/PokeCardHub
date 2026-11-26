package com.basespringboot.service;

import java.util.List;
import java.util.Optional;

import com.basespringboot.entity.CardSet;

public interface CardSetService {

    Optional<CardSet> create(CardSet cardSet, String seriesId);

    Optional<CardSet> getById(String id);

    List<CardSet> findAll();

    List<CardSet> findBySeries(String seriesId);

    Optional<CardSet> update(String id, CardSet changes);

    boolean delete(String id);
}
