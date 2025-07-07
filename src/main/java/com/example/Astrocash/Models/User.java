package com.example.Astrocash.Models;



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

    @Id
    private Long id;

    private String nome;

    private String email;

    private String Senha;

    private String role = "USER";

}
