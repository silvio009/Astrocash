package com.example.Astrocash.repository;

import com.example.Astrocash.models.Crypto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoRepository extends MongoRepository<Crypto,String> {

}
