package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basespringboot.entity.Series;


public interface SeriesRepository extends JpaRepository<Series, String> {}
