package com.example.Astrocash.controllers;


import com.example.Astrocash.dto.stock.DetailsStockDto;
import com.example.Astrocash.dto.stock.ListingStockDto;
import com.example.Astrocash.dto.stock.RegisterStockDto;
import com.example.Astrocash.dto.stock.UpadateStockDto;
import com.example.Astrocash.models.stocks.Stock;
import com.example.Astrocash.repository.StockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public ResponseEntity<List<ListingStockDto>> ListingStock (Pageable pageable){
        var page = stockRepository.findAll(pageable).stream().map(ListingStockDto :: new).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsStockDto> ListingStockId (@PathVariable ("id")String id){
        var stock = stockRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Stock ou ação não localizada"));
        return ResponseEntity.ok(new DetailsStockDto(stock));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsStockDto> registerStock(@RequestBody @Valid RegisterStockDto registerStockDto, UriComponentsBuilder uriComponentsBuilder){
        var stock = new Stock(registerStockDto);
        stockRepository.save(stock);
        var uri = uriComponentsBuilder.path("/stock/{id}").buildAndExpand(stock.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsStockDto(stock));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetailsStockDto> UpdateDataStock (@PathVariable("id") String id, @RequestBody UpadateStockDto upadateStockDto){
        var stock = stockRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Stock ou ação não localizada"));
        stock.UpadateWith(upadateStockDto);
        stockRepository.save(stock);
        return ResponseEntity.ok(new DetailsStockDto(stock));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deleteStock(@PathVariable("id") String id){
      stockRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
}
