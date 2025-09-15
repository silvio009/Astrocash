package com.example.Astrocash.dto.user;

import com.example.Astrocash.models.users.Email;
import com.example.Astrocash.models.users.Senha;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegisterUserDto(
         @NotBlank(message = "O nome é obrigatorio para o registro")
         @NotNull
         String nome,
         @NotNull(message = "O E-mail é obrigatorio para o registro")
         Email email,
         @NotNull(message = "A senha é obrigatoria para o registro")
         Senha senha,

         @NotNull(message = "O cpf é obrigatorio para o registro")
         String cpf,
         String role) {

}
