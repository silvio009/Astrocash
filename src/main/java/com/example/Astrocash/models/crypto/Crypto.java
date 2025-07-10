package com.example.Astrocash.models.crypto;


import com.example.Astrocash.dto.cryto.UpdateCryptoDto;
import com.example.Astrocash.dto.cryto.RegisterCryptoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cryptos")
public class Crypto {

    @Id
    private String id;
    private Simbolo simbolo; // ex: BTC, ETH
    private String nome;
    private double precoAtual;
    private double variacaoPercentual;

    public Crypto(RegisterCryptoDto registerCryptoDto) {
        simbolo = registerCryptoDto.simbolo();
        nome = registerCryptoDto.nome();
        precoAtual = registerCryptoDto.precoAtual();
        variacaoPercentual = registerCryptoDto.variacaoPercentual();
    }

    public void updateWith(UpdateCryptoDto updateCryptoDto) {
        if (updateCryptoDto.simbolo() != null){
            simbolo = updateCryptoDto.simbolo();
        }
        if (updateCryptoDto.nome() != null){
            nome = updateCryptoDto.nome();
        }
        if (updateCryptoDto.precoAtual() != null){
            precoAtual = updateCryptoDto.precoAtual();
        }
        if (updateCryptoDto.variacaoPercentual() != null){
            variacaoPercentual = updateCryptoDto.variacaoPercentual();
        }
    }
}