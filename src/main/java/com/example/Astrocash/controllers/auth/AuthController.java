package com.example.Astrocash.controllers.auth;

import com.example.Astrocash.dto.user.RegisterUserDto;
import com.example.Astrocash.models.users.Senha;
import com.example.Astrocash.models.users.User;
import com.example.Astrocash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto dto) {
        String senhaCriptografada = passwordEncoder.encode(dto.senha().getValue());

        User user = new User();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setSenha(new Senha(senhaCriptografada));
        user.setRole(dto.role());

        userRepository.save(user);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}