package com.example.Astrocash.controllers;

import com.example.Astrocash.dto.portfolio.DetailsPortfolioDto;
import com.example.Astrocash.dto.portfolio.ListingPortfolioDto;
import com.example.Astrocash.dto.portfolio.RegisterPortfolioDto;
import com.example.Astrocash.dto.portfolio.UpadatePortfolioDto;
import com.example.Astrocash.dto.stock.DetailsStockDto;
import com.example.Astrocash.dto.stock.ListingStockDto;
import com.example.Astrocash.dto.stock.RegisterStockDto;
import com.example.Astrocash.dto.stock.UpadateStockDto;
import com.example.Astrocash.repository.PortfolioItemRepository;
import com.example.Astrocash.service.PortfolioService;
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
@RequestMapping("/portfolio")
@CrossOrigin(origins = "*")
public class PortfolioItemController {

    @Autowired
    private PortfolioItemRepository portfolioItemRepository;

    @Autowired
    private PortfolioService portfolioService;


    @GetMapping
    public ResponseEntity<List<ListingPortfolioDto>> Listingportfolio (Pageable pageable){
        var page = portfolioItemRepository.findAll(pageable).stream().map(ListingPortfolioDto :: new).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsPortfolioDto> ListingPortfolioId (@PathVariable("id")String id){
        var portfolio = portfolioItemRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Portfolio não localizado"));
        return ResponseEntity.ok(new DetailsPortfolioDto(portfolio));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsPortfolioDto> registerPortfolio(
            @RequestBody @Valid RegisterPortfolioDto registerPortfolioDto,
            UriComponentsBuilder uriComponentsBuilder) {

        var portfolio = portfolioService.cadastrarPortfolio(registerPortfolioDto);
        var uri = uriComponentsBuilder.path("/portfolio/{id}").buildAndExpand(portfolio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsPortfolioDto(portfolio));
    }


    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetailsPortfolioDto> UpdateDataPortfolio (@PathVariable("id") String id, @RequestBody UpadatePortfolioDto upadatePortfolioDto){
        var portfolio = portfolioItemRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Portfolio não localizado"));
        portfolio.UpadateWith(upadatePortfolioDto);
        portfolioItemRepository.save(portfolio);
        return ResponseEntity.ok(new DetailsPortfolioDto(portfolio));
    }


    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletePortfolio(@PathVariable("id") String id){
        portfolioItemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
