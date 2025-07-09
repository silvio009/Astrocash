package com.example.Astrocash.repository;

import com.example.Astrocash.models.stocks.Stock;
import com.example.Astrocash.models.stocks.Ticker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock,String> {
    List<Stock> findByMercado(String Mercado);
    List<Stock> findByPais(String pais);

    boolean existsByTicker(Ticker ticker);
    boolean existsByNome(String nome);
}
