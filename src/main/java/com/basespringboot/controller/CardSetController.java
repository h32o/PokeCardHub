package com.basespringboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.basespringboot.dto.CardSetRequest;
import com.basespringboot.dto.CardSetResponse;
import com.basespringboot.service.CardSetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sets")
@RequiredArgsConstructor
public class CardSetController {

    private final CardSetService cardSetService;

    @PostMapping
    public ResponseEntity<CardSetResponse> create(@Valid @RequestBody CardSetRequest request) {
        // empty Optional = unknown seriesId in the body -> 404 (bad payload would be caught by @Valid as 400)
        return cardSetService.create(request.toEntity(), request.seriesId())
                .map(set -> ResponseEntity.status(HttpStatus.CREATED).body(CardSetResponse.from(set)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CardSetResponse> findAll() {
        return cardSetService.findAll().stream().map(CardSetResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardSetResponse> getById(@PathVariable String id) {
        return cardSetService.getById(id)
                .map(set -> ResponseEntity.ok(CardSetResponse.from(set)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Nested route: all sets of one series (REST hierarchy, course Level 1)
    @GetMapping("/api/series/{seriesId}/sets")
    public List<CardSetResponse> findBySeries(@PathVariable String seriesId) {
        return cardSetService.findBySeries(seriesId).stream().map(CardSetResponse::from).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardSetResponse> update(@PathVariable String id,
                                                  @Valid @RequestBody CardSetRequest request) {
        return cardSetService.update(id, request.toEntity())
                .map(set -> ResponseEntity.ok(CardSetResponse.from(set)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return cardSetService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
