package com.basespringboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.basespringboot.dto.CardRequest;
import com.basespringboot.dto.CardResponse;
import com.basespringboot.service.CardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponse> create(@Valid @RequestBody CardRequest request) {
        // empty Optional = a business rule failed (unknown set, duplicate localId, hp on a
        // non-POKEMON card). Without an error-handling layer we cannot tell which one:
        // the API answers 400. (@Valid already covers malformed payloads with a default 400.)
        return cardService.create(request)
                .map(card -> ResponseEntity.status(HttpStatus.CREATED).body(CardResponse.from(card)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public List<CardResponse> findAll() {
        return cardService.findAll().stream().map(CardResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> getById(@PathVariable String id) {
        return cardService.getById(id)
                .map(card -> ResponseEntity.ok(CardResponse.from(card)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponse> update(@PathVariable String id,
                                               @Valid @RequestBody CardRequest request) {
        return cardService.update(id, request)
                .map(card -> ResponseEntity.ok(CardResponse.from(card)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return cardService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
