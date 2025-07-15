package com.example.Astrocash.controllers;

import com.example.Astrocash.dto.user.DetailsUsersDto;
import com.example.Astrocash.dto.user.ListingUsersDto;
import com.example.Astrocash.dto.user.RegisterUserDto;
import com.example.Astrocash.dto.user.UpdateUsersDto;
import com.example.Astrocash.repository.UserRepository;
import com.example.Astrocash.service.UserService;
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
@RequestMapping("/users")
@CrossOrigin(origins = "*") // Habilita requisições de qualquer origem, útil para o frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<ListingUsersDto>> ListingUsers (Pageable pageable){
        var page = userRepository.findAll(pageable).stream().map(ListingUsersDto :: new ).toList();
        return ResponseEntity.ok(page);

    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsUsersDto> ListingUsersId (@PathVariable("id") String id){
        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
        return  ResponseEntity.ok(new DetailsUsersDto(user));
    }

    @PostMapping
    public ResponseEntity<DetailsUsersDto> registerUser (@RequestBody @Valid RegisterUserDto registerUserDto, UriComponentsBuilder uriComponentsBuilder){
        var user = userService.cadastrarUsuario(registerUserDto);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUsersDto(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<DetailsUsersDto> UpdateDataUser (@PathVariable("id") String id,@RequestBody UpdateUsersDto updateUsersDto ){

        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
        user.UpdateDataUser(updateUsersDto);
        userRepository.save(user);
        return ResponseEntity.ok(new DetailsUsersDto(user));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser (@PathVariable("id")String id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
