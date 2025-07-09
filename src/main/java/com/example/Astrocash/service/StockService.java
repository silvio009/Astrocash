package com.example.Astrocash.service;

import com.example.Astrocash.dto.stock.RegisterStockDto;
import com.example.Astrocash.models.stocks.Stock;
import com.example.Astrocash.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock cadastrarStock(RegisterStockDto registerStockDto) {

        if (stockRepository.existsByTicker(registerStockDto.ticker())) {
            throw new ResponseStatusException(CONFLICT, "Já existe uma ação com este ticker.");
        }

        if (stockRepository.existsByNome(registerStockDto.nome())) {
            throw new ResponseStatusException(CONFLICT, "Já existe uma ação com este nome.");
        }

        Stock stock = new Stock(registerStockDto);
        return stockRepository.save(stock);
    }
}
