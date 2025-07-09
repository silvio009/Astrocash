package com.example.Astrocash.dto;

import com.example.Astrocash.models.users.Email;
import com.example.Astrocash.models.users.Senha;
import com.example.Astrocash.models.users.User;

public record ListingUsersDto(String id, String nome, Email email, Senha senha, String role) {
    public ListingUsersDto(User user){
        this(user.getId(), user.getNome(),user.getEmail(), user.getSenha(), user.getRole());
    }
}
