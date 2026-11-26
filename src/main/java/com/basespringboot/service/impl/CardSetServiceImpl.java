package com.basespringboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basespringboot.entity.CardSet;
import com.basespringboot.repository.CardSetRepository;
import com.basespringboot.service.CardSetService;
import com.basespringboot.service.SeriesService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CardSetServiceImpl implements CardSetService {

    private final CardSetRepository cardSetRepository;
    private final SeriesService seriesService;

    @Override
    public Optional<CardSet> create(CardSet cardSet, String seriesId) {
        return seriesService.getById(seriesId)
                .map(series -> {
                    cardSet.setSeries(series);
                    return cardSetRepository.save(cardSet);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CardSet> getById(String id) {
        return cardSetRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardSet> findAll() {
        return cardSetRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardSet> findBySeries(String seriesId) {
        if (seriesService.getById(seriesId).isEmpty()) {
            return List.of();
        }
        return cardSetRepository.findBySeriesId(seriesId);
    }

    @Override
    public Optional<CardSet> update(String id, CardSet changes) {
        return cardSetRepository.findById(id)
                .map(set -> {
                    if (changes.getTcgOnline() != null) {
                        set.setTcgOnline(changes.getTcgOnline());
                    }
                    if (changes.getCardCountOfficial() != null) {
                        set.setCardCountOfficial(changes.getCardCountOfficial());
                    }
                    if (changes.getReleaseDate() != null) {
                        set.setReleaseDate(changes.getReleaseDate());
                    }
                    return set; // managed entity -> auto-UPDATE on flush
                });
    }

    @Override
    public boolean delete(String id) {
        if (cardSetRepository.existsById(id)) {
            cardSetRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
