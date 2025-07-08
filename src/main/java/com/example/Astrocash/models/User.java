package com.example.Astrocash.models;



import com.example.Astrocash.dto.RegisterUserDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@ToString
public class User {

    // como estou usando MongoDB o id pode ser um String

    @Id
    private String id;

    private String nome;

    private String email;

    private String senha;

    private String role = "USER";

    public User(RegisterUserDto registerUserDto) {
        nome = registerUserDto.nome();
        email = registerUserDto.email();
        senha = registerUserDto.senha();
        role = registerUserDto.role();
    }

}

// ANTES DE SUBIR FAZER UMA BRANCH DEV
