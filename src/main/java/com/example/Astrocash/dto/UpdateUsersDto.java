package com.example.Astrocash.dto;

import com.example.Astrocash.models.Email;
import com.example.Astrocash.models.Senha;

public record UpdateUsersDto(String id, String nome, Email email, Senha senha, String role) {
}
