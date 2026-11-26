package com.basespringboot.service;

import com.basespringboot.entity.Series;
import java.util.List;
import java.util.Optional;

public interface SeriesService {
    Series create(Series series);
    Optional<Series> getById(String id);
    List<Series> findAll();
    Optional<Series> update(String id, Series changes);
    boolean delete(String id);
}