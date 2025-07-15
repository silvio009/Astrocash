package com.example.Astrocash.repository;

import com.example.Astrocash.models.porfolio.PortfolioItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PortfolioItemRepository extends MongoRepository<PortfolioItem,String> {

    boolean existsByUserId (String userid);

    boolean existsByAtivoId (String ativoid);


}
