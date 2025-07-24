package com.example.Astrocash.service;

import com.example.Astrocash.dto.transaction.RegisterTransactionDto;
import com.example.Astrocash.models.transaction.Transaction;
import com.example.Astrocash.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction cadastrarTransaction(RegisterTransactionDto registerTransactionDto,String userId){



        if (transactionRepository.existsByUserId(userId)){
            throw new ResponseStatusException(CONFLICT,"Já existe esse UserId no banco");
        }
        if (transactionRepository.existsByAtivoId(registerTransactionDto.ativoId())){
            throw new ResponseStatusException(CONFLICT,"Já existe esse ativoid no banco");
        }
        Transaction transaction = new Transaction(registerTransactionDto,userId);
        return transactionRepository.save(transaction);
    }

}
