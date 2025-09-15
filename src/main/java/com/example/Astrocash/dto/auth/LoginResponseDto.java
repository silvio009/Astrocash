package com.example.Astrocash.dto.auth;

import com.example.Astrocash.models.users.Email;
import com.example.Astrocash.models.users.Senha;

import java.time.LocalDateTime;
import java.util.Date;

public record LoginResponseDto(String token, String id, String nome, Email email, String cpf, Date dataCadastro) {}