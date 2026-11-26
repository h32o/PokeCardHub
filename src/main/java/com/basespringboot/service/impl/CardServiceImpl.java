package com.basespringboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basespringboot.dto.CardRequest;
import com.basespringboot.entity.Card;
import com.basespringboot.repository.CardRepository;
import com.basespringboot.service.CardService;
import com.basespringboot.service.CardSetService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardSetService cardSetService;

    @Override
    public Optional<Card> create(CardRequest request) {
        if (!isValid(request, null)) {
            return Optional.empty();
        }
        return cardSetService.getById(request.setId())
                .map(set -> cardRepository.save(toCard(request, set)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Card> getById(String id) {
        return cardRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> update(String id, CardRequest request) {
        if (!isValid(request, id)) {
            return Optional.empty();
        }
        return cardRepository.findById(id)
                .map(card -> {
                    card.setCategory(request.category());
                    card.setHp(request.hp());
                    card.setRarity(request.rarity());
                    card.setStage(request.stage());
                    return card;
                });
    }

    @Override
    public boolean delete(String id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean isValid(CardRequest request, String selfId) {
        if (cardSetService.getById(request.setId()).isEmpty()) {
            return false;
        }

        boolean duplicate = (selfId == null)
                ? cardRepository.existsByCardSet_IdAndLocalId(request.setId(), request.localId())
                : cardRepository.existsByCardSet_IdAndLocalIdAndIdNot(request.setId(), request.localId(), selfId);
        if (duplicate) {
            return false;
        }

        if (request.hp() != null && !"POKEMON".equals(request.category())) {
            return false;
        }

        if (request.stage() != null && !"POKEMON".equals(request.category())) {
            return false;
        }

        return true;
    }

    private Card toCard(CardRequest request, com.basespringboot.entity.CardSet set) {
        Card card = new Card();
        card.setId(request.id());
        card.setCardSet(set);
        card.setLocalId(request.localId());
        card.setCategory(request.category());
        card.setHp(request.hp());
        card.setRarity(request.rarity());
        card.setStage(request.stage());
        card.setFilePath(request.filePath());
        return card;
    }
}
