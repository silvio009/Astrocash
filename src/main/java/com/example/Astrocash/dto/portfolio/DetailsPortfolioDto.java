package com.example.Astrocash.dto.portfolio;

import com.example.Astrocash.models.PortfolioItem;

public record DetailsPortfolioDto(String id, String userId, String ativoId , String tipoAtivo, Double quantidade, Double precoMedioCompra ) {
    public DetailsPortfolioDto(PortfolioItem portfolioItem){
        this(portfolioItem.getId(), portfolioItem.getUserId(), portfolioItem.getAtivoId(), portfolioItem.getTipoAtivo(), portfolioItem.getQuantidade(), portfolioItem.getPrecoMedioCompra());
    }
}
