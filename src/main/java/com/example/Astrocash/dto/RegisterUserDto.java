package com.example.Astrocash.dto;

import com.example.Astrocash.models.Email;
import com.example.Astrocash.models.Senha;
import com.example.Astrocash.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDto(
         @NotBlank(message = "O nome é obrigatorio para o registro")
         @NotNull
         String nome,
         @NotNull(message = "O E-mail é obrigatorio para o registro")
         Email email,
         @NotNull(message = "A senha é obrigatoria para o registro")
         Senha senha,

         String role) {

}
