package com.example.Astrocash.controllers;

import com.example.Astrocash.dto.DetailsUsersDto;
import com.example.Astrocash.dto.ListingUsersDto;
import com.example.Astrocash.dto.RegisterUserDto;
import com.example.Astrocash.dto.UpdateUsersDto;
import com.example.Astrocash.models.User;
import com.example.Astrocash.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // Habilita requisições de qualquer origem, útil para o frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List<ListingUsersDto>> listaget(Pageable pageable){
        var page = userRepository.findAll(pageable).stream().map(ListingUsersDto :: new ).toList();
        return ResponseEntity.ok(page);

    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsUsersDto> getId (@PathVariable("id") String id){
        var user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        return  ResponseEntity.ok(new DetailsUsersDto(user));
    }

    @PostMapping
    public ResponseEntity<DetailsUsersDto> post (@RequestBody @Valid RegisterUserDto registerUserDto, UriComponentsBuilder uriComponentsBuilder){
        var user = new User(registerUserDto);
        userRepository.save(user);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUsersDto(user));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete (@PathVariable("id")String id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DetailsUsersDto> update (@PathVariable("id") String id,@RequestBody UpdateUsersDto updateUsersDto ){

        var user = userRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Usuário não encontrado para atualizar"));
        user.UpdateDataUser(updateUsersDto);
        userRepository.save(user);
        return ResponseEntity.ok(new DetailsUsersDto(user));
    }

}
