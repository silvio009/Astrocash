package com.example.Astrocash.models.stocks;


import com.example.Astrocash.dto.stock.RegisterStockDto;
import com.example.Astrocash.dto.stock.UpadateStockDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stocks")
public class Stock {

    @Id
    private String id;
    private Ticker ticker;
    private String nome;
    private Mercado mercado;
    private Pais pais;
    private double precoAtual;
    private double variacaoPercentual;

    public Stock(RegisterStockDto registerStockDto) {
        ticker = registerStockDto.ticker();
        nome = registerStockDto.nome();
        mercado = registerStockDto.mercado();
        pais = registerStockDto.pais();
        precoAtual = registerStockDto.precoAtual();
        variacaoPercentual = registerStockDto.variacaoPercentual();
    }

    public void UpadateWith(UpadateStockDto upadateStockDto) {
        if (upadateStockDto.ticker() != null){
            ticker = upadateStockDto.ticker();
        }
        if (upadateStockDto.mercado() != null){
            mercado = upadateStockDto.mercado();
        }
        if (upadateStockDto.pais() != null){
            pais = upadateStockDto.pais();
        }
        if (upadateStockDto.precoAtual() != null){
            precoAtual = upadateStockDto.precoAtual();
        }
        if (upadateStockDto.variacaoPercentual() != null){
            variacaoPercentual = upadateStockDto.variacaoPercentual();
        }
    }
}