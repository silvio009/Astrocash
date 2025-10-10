package com.example.Astrocash.dto.user;

import com.example.Astrocash.models.users.Email;
import com.example.Astrocash.models.users.Endereco;
import com.example.Astrocash.models.users.Senha;
import com.example.Astrocash.models.users.User;

import java.util.Date;

public record DetailsUsersDto(String id, String nome, Email email, Senha senha, String role, String cpf, Date dataCadastro, String telefone, Endereco endereco, String fotoPerfil) {
    public DetailsUsersDto(User user){
        this(user.getId(),user.getNome(),user.getEmail(),user.getSenha(),user.getRole(), user.getCpf(),user.getDataCadastro(),user.getTelefone(),user.getEndereco(), user.getFotoPerfil());
    }
}
