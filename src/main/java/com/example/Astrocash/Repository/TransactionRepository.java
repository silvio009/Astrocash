package com.example.Astrocash.Repository;

import com.example.Astrocash.Models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction,Long> {
    List<Transaction> findByUserId(String userid);
}
