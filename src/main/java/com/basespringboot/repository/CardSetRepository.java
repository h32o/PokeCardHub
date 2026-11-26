package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basespringboot.entity.CardSet;

public interface CardSetRepository extends JpaRepository<CardSet, String> {}
