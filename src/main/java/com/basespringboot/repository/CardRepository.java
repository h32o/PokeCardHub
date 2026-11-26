package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basespringboot.entity.Card;

public interface CardRepository extends JpaRepository<Card, String> {}
