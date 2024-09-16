package br.fernandapcaetano.criptography_java_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fernandapcaetano.criptography_java_spring.dto.TransactionDTO;
import br.fernandapcaetano.criptography_java_spring.dto.TransactionRequestDTO;
import br.fernandapcaetano.criptography_java_spring.exception.ResourceNotFoundException;
import br.fernandapcaetano.criptography_java_spring.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Criptografia de transação de conta", description = "Realizando a criptografia em tempo de execução durante a conversão da entidade para a coluna correspondente no banco de dados")
@RestController
@RequestMapping("${api.prefix}")
public class TransactionController {
    @Autowired TransactionService service;
    
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TransactionRequestDTO dto){
            service.save(dto);
            return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAll() {
        List<TransactionDTO> dtos = service.findAll();
        return ResponseEntity.ok().body(dtos);
    }

    @PutMapping
    public ResponseEntity<TransactionDTO> putMethodName(@RequestBody TransactionDTO dto) {
        if (dto.id() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            TransactionDTO newDto = service.update(dto);
            return ResponseEntity.ok().body(newDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
