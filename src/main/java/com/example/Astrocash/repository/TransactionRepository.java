package com.example.Astrocash.repository;

import com.example.Astrocash.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction,String> {

    boolean existsByUserId (String userid);

    boolean existsByAtivoId (String ativoid);
}
