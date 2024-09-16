package br.fernandapcaetano.criptography_java_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransactionRequestDTO(
    @NotBlank
    String userDocument,
    @NotBlank
    String creditCardToken,
    @NotNull
    Long transactionValue
) {}
