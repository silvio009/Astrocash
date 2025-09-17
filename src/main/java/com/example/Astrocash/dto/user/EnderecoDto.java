package com.example.Astrocash.dto.user;

import com.example.Astrocash.models.users.Endereco;

public record EnderecoDto(
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
    public Endereco toEndereco() {
        return new Endereco(rua, numero, bairro, cidade, estado, cep);
    }
}
