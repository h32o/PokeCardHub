package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basespringboot.entity.Card;

public interface CardRepository extends JpaRepository<Card, String> {

    boolean existsByCardSet_IdAndLocalId(String setId, String localId);

    boolean existsByCardSet_IdAndLocalIdAndIdNot(String setId, String localId, String id);
}
