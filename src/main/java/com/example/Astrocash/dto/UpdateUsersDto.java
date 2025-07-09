package com.example.Astrocash.dto;

import com.example.Astrocash.models.users.Email;
import com.example.Astrocash.models.users.Senha;

public record UpdateUsersDto(String id, String nome, Email email, Senha senha, String role) {
}
