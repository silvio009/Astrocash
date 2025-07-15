package com.example.Astrocash.repository;

import com.example.Astrocash.models.PortfolioItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PortfolioItemRepository extends MongoRepository<PortfolioItem,String> {

    boolean existsByUserId (String userid);

    boolean existsByAtivoId (String ativoid);


}
