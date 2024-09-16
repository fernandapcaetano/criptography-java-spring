package br.fernandapcaetano.criptography_java_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fernandapcaetano.criptography_java_spring.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long>{
    
}
