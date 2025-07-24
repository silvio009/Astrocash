package com.example.Astrocash.models.transaction;


import com.example.Astrocash.dto.transaction.RegisterTransactionDto;
import com.example.Astrocash.dto.transaction.UpadateTransactionDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;


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

    private TipoAtivo tipoAtivo; // "STOCK" ou "CRYPTO"
    private double quantidade;
    private double precoUnitario;
    private TipoOperacao tipoOperacao; // "COMPRA" ou "VENDA"
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    private LocalDateTime dataHora;

    public Transaction(RegisterTransactionDto registerTransactionDto,String userId) {
        // Mudar apos colocar token JWT
        this.userId = userId;
        this.ativoId = registerTransactionDto.ativoId();

        this.tipoAtivo = registerTransactionDto.tipoAtivo();
        this.quantidade = registerTransactionDto.quantidade();
        this.precoUnitario = registerTransactionDto.precoUnitario();
        this.tipoOperacao = registerTransactionDto.tipoOperacao();
        this.dataHora = LocalDateTime.now();
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
}