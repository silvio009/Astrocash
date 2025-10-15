package com.example.Astrocash.dto;

public record ChangePasswordDto(
        String senhaAtual,
        String novaSenha,
        String confirmarSenha
) {}