package br.fernandapcaetano.criptography_java_spring.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fernandapcaetano.criptography_java_spring.dto.TransactionDTO;
import br.fernandapcaetano.criptography_java_spring.dto.TransactionRequestDTO;
import br.fernandapcaetano.criptography_java_spring.exception.ResourceNotFoundException;
import br.fernandapcaetano.criptography_java_spring.model.TransactionModel;
import br.fernandapcaetano.criptography_java_spring.repository.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired TransactionRepository transactionRepository;

    public TransactionModel save(TransactionRequestDTO dto){
        TransactionModel model = new TransactionModel();
        model.setUserDocument(dto.userDocument());
        model.setCreditCardToken(dto.creditCardToken());
        model.setTransactionValue(dto.transactionValue());
        return transactionRepository.save(model);
    }

    public List<TransactionDTO> findAll(){
        List<TransactionModel> models = transactionRepository.findAll();
        List<TransactionDTO> dtos = new ArrayList<>();
        for (TransactionModel model : models) {
            Long id = model.getId();
            String userDocument = model.getUserDocument();
            String creditCardToken = model.getCreditCardToken();
            Long transactionValue = model.getTransactionValue();
            TransactionDTO dto = new TransactionDTO(id, userDocument, creditCardToken, transactionValue);
            dtos.add(dto);
        }
        return dtos;
    }

    public TransactionDTO update(TransactionDTO dto){
        if (dto.id() == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (!transactionRepository.existsById(dto.id())) {
            throw new ResourceNotFoundException("Transaction not found with ID: " + dto.id());
        }

        TransactionModel transaction = transactionRepository.findById(dto.id()).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        transaction.setUserDocument(dto.userDocument());
        transaction.setCreditCardToken(dto.creditCardToken());
        transaction.setTransactionValue(dto.transactionValue());
        transactionRepository.save(transaction);

        return new TransactionDTO(transaction.getId(), transaction.getUserDocument(), transaction.getCreditCardToken(), transaction.getTransactionValue());
    }

    public void delete(Long id){
        if (!transactionRepository.existsById(id)) throw new RuntimeException("Esse id Não Existe");
        transactionRepository.deleteById(id);
    }
}
