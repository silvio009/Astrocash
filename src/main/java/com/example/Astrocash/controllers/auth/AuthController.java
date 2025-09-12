package com.example.Astrocash.controllers.auth;

import com.example.Astrocash.dto.user.RegisterUserDto;
import com.example.Astrocash.models.users.Senha;
import com.example.Astrocash.models.users.User;
import com.example.Astrocash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterUserDto dto) {
        Map<String, Object> response = new HashMap<>();


        if (userRepository.existsByEmail(dto.email())) {
            response.put("erro", "Este e-mail já está sendo utilizado.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        try {
            String senhaCriptografada = passwordEncoder.encode(dto.senha().getValue());

            User user = new User();
            user.setNome(dto.nome());
            user.setEmail(dto.email());
            user.setSenha(new Senha(senhaCriptografada));
            user.setRole(dto.role());

            userRepository.save(user);

            // Retorna sucesso em JSON
            response.put("mensagem", "Usuário registrado com sucesso! Parabéns, agora você pode começar a investir no seu futuro financeiro 🚀");
            response.put("token", null);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("erro", "Ocorreu um erro ao criar sua conta.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
