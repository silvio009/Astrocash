package com.example.Astrocash.controllers;

import com.example.Astrocash.dto.DetailsUsersDto;
import com.example.Astrocash.dto.RegisterUserDto;
import com.example.Astrocash.models.User;
import com.example.Astrocash.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // Habilita requisições de qualquer origem, útil para o frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<DetailsUsersDto> post (@RequestBody @Valid RegisterUserDto registerUserDto, UriComponentsBuilder uriComponentsBuilder){
        var user = new User(registerUserDto);
        userRepository.save(user);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUsersDto(user));
    }
}
