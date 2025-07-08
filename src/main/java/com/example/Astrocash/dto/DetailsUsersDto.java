package com.example.Astrocash.dto;

import com.example.Astrocash.models.User;

public record DetailsUsersDto(String id, String nome, String email, String senha, String role) {
    public DetailsUsersDto(User user){
        this(user.getId(),user.getNome(),user.getEmail(),user.getSenha(),user.getRole());
    }
}
