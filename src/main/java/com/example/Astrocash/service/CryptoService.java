package com.example.Astrocash.service;

import com.example.Astrocash.dto.cryto.RegisterCryptoDto;
import com.example.Astrocash.models.crypto.Crypto;
import com.example.Astrocash.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class CryptoService {
    @Autowired
    private CryptoRepository cryptoRepository;


    public Crypto CadastrarCrypto(RegisterCryptoDto registerCryptoDto){

        if (cryptoRepository.existsBySimbolo(registerCryptoDto.simbolo())){
            throw new ResponseStatusException(CONFLICT,"Já existe uma crypto com esse simbolo");
        }
        if (cryptoRepository.existsByNome(registerCryptoDto.nome())){
            throw new ResponseStatusException(CONFLICT, "Já existe uma crypto com esse nome");
        }
        Crypto crypto = new Crypto(registerCryptoDto);
        return cryptoRepository.save(crypto);

    }

}
