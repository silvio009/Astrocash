package com.example.Astrocash.dto;

import com.example.Astrocash.models.User;

public record ListingUsersDto(String id, String nome, String email, String senha, String role) {
    public ListingUsersDto(User user){
        this(user.getId(), user.getNome(),user.getEmail(), user.getSenha(), user.getRole());
    }
}
