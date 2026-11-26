package com.basespringboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basespringboot.entity.Series;
import com.basespringboot.repository.SeriesRepository;
import com.basespringboot.service.SeriesService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Override
    public Series create(Series series) {
        return seriesRepository.save(series);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Series> getById(String id) {
        return seriesRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Series> findAll() {
        return seriesRepository.findAll();
    }

    @Override
    public Optional<Series> update(String id, Series changes) {
        return seriesRepository.findById(id)
                .map(series -> {
                    series.setEnergies(changes.getEnergies());
                    return series;
                });
    }

    @Override
    public boolean delete(String id) {
        if (seriesRepository.existsById(id)) {
            seriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
