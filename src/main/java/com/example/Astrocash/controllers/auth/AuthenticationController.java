package com.example.Astrocash.controllers.auth;

import com.example.Astrocash.dto.auth.AuthenticationDto;
import com.example.Astrocash.dto.auth.LoginResponseDto;
import com.example.Astrocash.models.users.User;
import com.example.Astrocash.repository.UserRepository;
import com.example.Astrocash.service.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto authDto) {
        try {
            User user = userRepository.findByEmail_Value(authDto.email())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            System.out.println("Senha criptografada no banco: " + user.getPassword());
            System.out.println("Senha recebida no login: " + authDto.senha());
            System.out.println("Password matches? " + passwordEncoder.matches(authDto.senha(), user.getPassword()));

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(authDto.email(), authDto.senha());

            Authentication auth = authenticationManager.authenticate(token);
            String jwt = tokenService.gerarToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDto(jwt));

        } catch (Exception e) {
            System.out.println("Falha no login: " + e.getMessage());
            return ResponseEntity.status(401).body("Falha na autenticação: " + e.getMessage());
        }
    }
}

