package br.fernandapcaetano.criptography_java_spring.model;

import br.fernandapcaetano.criptography_java_spring.model.converter.CryptorConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(name = "transaction")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Convert(converter = CryptorConverter.class)
    private String userDocument;

    @Convert(converter = CryptorConverter.class)
    private String creditCardToken;
    
    private Long transactionValue;
}
