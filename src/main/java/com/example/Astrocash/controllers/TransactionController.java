package com.example.Astrocash.controllers;

import com.example.Astrocash.dto.transaction.DetailsTransactionDto;
import com.example.Astrocash.dto.transaction.ListingTransactionDto;
import com.example.Astrocash.dto.stock.DetailsStockDto;
import com.example.Astrocash.dto.stock.RegisterStockDto;
import com.example.Astrocash.dto.stock.UpadateStockDto;
import com.example.Astrocash.dto.transaction.RegisterTransactionDto;
import com.example.Astrocash.dto.transaction.UpadateTransactionDto;
import com.example.Astrocash.repository.TransactionRepository;
import com.example.Astrocash.service.TransactionService;
import com.example.Astrocash.service.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TokenService tokenService;


    @GetMapping
    public ResponseEntity<List<ListingTransactionDto>> Listingtransaction (Pageable pageable){
        var page = transactionRepository.findAll(pageable).stream().map(ListingTransactionDto :: new).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsTransactionDto> ListingtransactionId (@PathVariable("id")String id){
        var transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction não localizada"));
        return ResponseEntity.ok(new DetailsTransactionDto(transaction));
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DetailsTransactionDto> registertransaction(
            @RequestBody @Valid RegisterTransactionDto registerTransactionDto,
            @RequestHeader("Authorization") String authorizationHeader,
            UriComponentsBuilder uriComponentsBuilder) {

        String token = authorizationHeader.replace("Bearer ", "");
        String userId = tokenService.getSubject(token);

        var transaction = transactionService.cadastrarTransaction(registerTransactionDto,userId);
        var uri = uriComponentsBuilder.path("/transaction/{id}").buildAndExpand(transaction.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsTransactionDto(transaction));
    }


    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetailsTransactionDto> UpdateDatatransaction (@PathVariable("id") String id, @RequestBody UpadateTransactionDto upadateTransactionDto){
        var transaction = transactionRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction não localizada"));

        transaction.UpadateWith(upadateTransactionDto);
        transactionRepository.save(transaction);
        return ResponseEntity.ok(new DetailsTransactionDto(transaction));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletetransaction(@PathVariable("id") String id){
        transactionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
