package com.example.Astrocash.models;


import com.example.Astrocash.dto.transaction.RegisterTransactionDto;
import com.example.Astrocash.dto.transaction.UpadateTransactionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    private String userId;
    private String ativoId;

    private String tipoAtivo; // "STOCK" ou "CRYPTO"
    private double quantidade;
    private double precoUnitario;
    private String tipoOperacao; // "COMPRA" ou "VENDA"
    private Instant dataHora;

    public Transaction(RegisterTransactionDto registerTransactionDto) {
        // Mudar apos colocar token JWT
        this.userId = registerTransactionDto.userId();
        this.ativoId = registerTransactionDto.ativoId();

        this.tipoAtivo = registerTransactionDto.tipoAtivo();
        this.quantidade = registerTransactionDto.quantidade();
        this.precoUnitario = registerTransactionDto.precoUnitario();
        this.tipoOperacao = registerTransactionDto.tipoOperacao();
        this.dataHora = Instant.now();
    }


    public void UpadateWith(UpadateTransactionDto upadateTransactionDto) {
        if (upadateTransactionDto.tipoAtivo() != null){
            tipoAtivo = upadateTransactionDto.tipoAtivo();
        }
        if (upadateTransactionDto.quantidade() != null){
            quantidade = upadateTransactionDto.quantidade();
        }
        if (upadateTransactionDto.precoUnitario() != null){
            precoUnitario =upadateTransactionDto.precoUnitario();
        }
        if (upadateTransactionDto.tipoOperacao() != null){
            tipoOperacao= upadateTransactionDto.tipoOperacao();
        }
        if (upadateTransactionDto.dataHora() != null){
            dataHora= upadateTransactionDto.dataHora();
        }
    }
    // COLOCAR VALIDAÇÃO DE (COMPRA E VENDA MAIS O TIPO DE ATIVO) ARRUMAR O DATAHORA PRA VIR NO PADRÃO AMERICA DO SUL
}