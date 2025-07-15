package com.example.Astrocash.service;

import com.example.Astrocash.dto.portfolio.RegisterPortfolioDto;
import com.example.Astrocash.models.PortfolioItem;
import com.example.Astrocash.repository.PortfolioItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class PortfolioService {

    @Autowired
    PortfolioItemRepository portfolioItemRepository;

    public PortfolioItem cadastrarPortfolio (RegisterPortfolioDto registerPortfolioDto){

        if (portfolioItemRepository.existsByUserId(registerPortfolioDto.userId())){
            throw new ResponseStatusException(CONFLICT,"Já existe esse UserId no banco");
        }
        if (portfolioItemRepository.existsByAtivoId(registerPortfolioDto.ativoId())){
            throw new ResponseStatusException(CONFLICT,"Já existe esse ativoid no banco");
        }
        PortfolioItem portfolioItem = new PortfolioItem(registerPortfolioDto);
        return portfolioItemRepository.save(portfolioItem);

    }
}
