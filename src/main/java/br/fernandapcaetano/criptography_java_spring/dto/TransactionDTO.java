package br.fernandapcaetano.criptography_java_spring.dto;


public record TransactionDTO(
    Long id,
    String userDocument,
    String creditCardToken,
    Long transactionValue) {}
