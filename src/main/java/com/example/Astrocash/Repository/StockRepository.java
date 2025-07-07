package com.example.Astrocash.Repository;

import com.example.Astrocash.Models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock,Long> {
    List<Stock> findByMercado(String Mercado);
    List<Stock> findByPais(String pais);
}
