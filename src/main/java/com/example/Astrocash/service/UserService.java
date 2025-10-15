package com.example.Astrocash.service;

import com.example.Astrocash.dto.ChangePasswordDto;
import com.example.Astrocash.dto.user.RegisterUserDto;
import com.example.Astrocash.dto.user.UpdateUserPhotoDto;
import com.example.Astrocash.models.users.Senha;
import com.example.Astrocash.models.users.User;
import com.example.Astrocash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User cadastrarUsuario(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário com este e-mail.");
        }
        return userRepository.save(new User(registerUserDto));
    }

    public User atualizarFotoPerfil(String userId, UpdateUserPhotoDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.updateFotoPerfil(dto.fotoPerfil());

        return userRepository.save(user);
    }


    public boolean alterarSenha(String id, ChangePasswordDto dto) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.senhaAtual(), user.getSenha().getValor())) {
            return false;
        }
        if (!dto.novaSenha().equals(dto.confirmarSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As novas senhas não coincidem");
        }
        user.setSenha(new Senha(passwordEncoder.encode(dto.novaSenha())));
        userRepository.save(user);
        return true;
    }



}