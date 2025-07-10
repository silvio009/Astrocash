package com.example.Astrocash.controllers;

import com.example.Astrocash.dto.cryto.UpdateCryptoDto;
import com.example.Astrocash.dto.cryto.DetailsCryptoDto;
import com.example.Astrocash.dto.cryto.ListingCryptoDto;
import com.example.Astrocash.dto.cryto.RegisterCryptoDto;
import com.example.Astrocash.models.crypto.Crypto;
import com.example.Astrocash.repository.CryptoRepository;
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
@RequestMapping("/crypto")
@CrossOrigin(origins = "*")
public class CryptoController {

    @Autowired
    private CryptoRepository cryptoRepository;

    @GetMapping
    public ResponseEntity<List<ListingCryptoDto>> ListingCrypto (Pageable pageable){
        var page = cryptoRepository.findAll(pageable).stream().map(ListingCryptoDto :: new).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsCryptoDto> ListingCryptoId (@PathVariable("id")String id){
        var crypto = cryptoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Crypto não foi localizada"));
        return ResponseEntity.ok(new DetailsCryptoDto(crypto));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsCryptoDto> RegisterCrypto (@RequestBody @Valid RegisterCryptoDto registerCryptoDto, UriComponentsBuilder uriComponentsBuilder){
        var crypto = new Crypto(registerCryptoDto);
        cryptoRepository.save(crypto);
        var uri = uriComponentsBuilder.path("/crypto/{id}").buildAndExpand(crypto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsCryptoDto(crypto));

    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetailsCryptoDto> updateCrypto(@PathVariable("id")String id, @RequestBody UpdateCryptoDto updateCryptoDto){
        var crypto = cryptoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cryto não foi localizada"));
        crypto.updateWith(updateCryptoDto);
        cryptoRepository.save(crypto);
        return ResponseEntity.ok(new DetailsCryptoDto(crypto));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCrypto (@PathVariable ("id") String id){
        cryptoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }




}
