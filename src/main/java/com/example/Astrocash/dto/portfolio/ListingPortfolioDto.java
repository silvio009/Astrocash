package com.example.Astrocash.dto.portfolio;

import com.example.Astrocash.models.porfolio.PortfolioItem;

public record ListingPortfolioDto(String id, String userId, String ativoId , String tipoAtivo, Double quantidade, Double precoMedioCompra ) {
    public  ListingPortfolioDto(PortfolioItem portfolioItem){
        this(portfolioItem.getId(), portfolioItem.getUserId(), portfolioItem.getAtivoId(), portfolioItem.getTipoAtivo(), portfolioItem.getQuantidade(), portfolioItem.getPrecoMedioCompra());
    }
}
