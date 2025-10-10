package com.example.Astrocash.service;

import com.example.Astrocash.dto.user.RegisterUserDto;
import com.example.Astrocash.dto.user.UpdateUserPhotoDto;
import com.example.Astrocash.models.users.User;
import com.example.Astrocash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User cadastrarUsuario(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário com este e-mail.");
        }
        return userRepository.save(new User(registerUserDto));
    }

    public User atualizarFotoPerfil(String userId, UpdateUserPhotoDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualizar campo de foto
        user.updateFotoPerfil(dto.fotoPerfil());

        // Salvar no banco
        return userRepository.save(user);
    }
}