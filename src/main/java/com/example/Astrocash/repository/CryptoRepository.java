package com.example.Astrocash.repository;

import com.example.Astrocash.models.crypto.Crypto;
import com.example.Astrocash.models.crypto.Simbolo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CryptoRepository extends MongoRepository<Crypto,String> {

    boolean existsBySimbolo (Simbolo simbolo);

    boolean existsByNome (String nome);

}
