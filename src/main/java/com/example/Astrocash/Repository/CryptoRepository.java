package com.example.Astrocash.Repository;

import com.example.Astrocash.Models.Crypto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoRepository extends MongoRepository<Crypto,Long> {

}
