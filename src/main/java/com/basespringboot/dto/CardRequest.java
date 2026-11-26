package com.basespringboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CardRequest(
        @NotBlank String id,
        @NotBlank String setId,
        @NotBlank String localId,
        @NotBlank @Pattern(regexp = "POKEMON|TRAINER|ENERGY",
                message = "category must be POKEMON, TRAINER or ENERGY") String category,
        @Min(0) Integer hp,
        String rarity,
        String stage,
        @NotBlank String filePath) {
}
