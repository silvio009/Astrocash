package com.example.Astrocash.dto;

import com.example.Astrocash.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDto(
         @NotBlank(message = "O nome é obrigatorio para o registro")
         @NotNull
         String nome,
         @NotBlank(message = "O E-mail é obrigatorio para o registro")
         @NotNull
         String email,
         @NotBlank(message = "A senha é obrigatoria para o registro")
         @NotNull
         String senha,

         String role) {

}
