package com.example.Astrocash.repository;

import com.example.Astrocash.models.transaction.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {

    boolean existsByUserId (String userid);

    boolean existsByAtivoId (String ativoid);
}
