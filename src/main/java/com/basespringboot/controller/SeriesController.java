package com.basespringboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.basespringboot.dto.SeriesRequest;
import com.basespringboot.dto.SeriesResponse;
import com.basespringboot.service.SeriesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService seriesService;

    @PostMapping
    public ResponseEntity<SeriesResponse> create(@Valid @RequestBody SeriesRequest request) {
        SeriesResponse created = SeriesResponse.from(seriesService.create(request.toEntity()));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<SeriesResponse> findAll() {
        return seriesService.findAll().stream().map(SeriesResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesResponse> getById(@PathVariable String id) {
        return seriesService.getById(id)
                .map(series -> ResponseEntity.ok(SeriesResponse.from(series)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesResponse> update(@PathVariable String id,
                                                @Valid @RequestBody SeriesRequest request) {
        return seriesService.update(id, request.toEntity())
                .map(series -> ResponseEntity.ok(SeriesResponse.from(series)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return seriesService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
