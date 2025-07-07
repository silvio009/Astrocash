package com.example.Astrocash.Repository;

import com.example.Astrocash.Models.PortfolioItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PortfolioItemRepository extends MongoRepository<PortfolioItem,Long> {
    List<PortfolioItem> findByUserId(String userId);
}
