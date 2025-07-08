package com.example.Astrocash.models;




import com.example.Astrocash.dto.RegisterUserDto;
import com.example.Astrocash.dto.UpdateUsersDto;
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

    private Email email;

    private Senha senha;

    private String role = "USER";

    public User(RegisterUserDto registerUserDto) {
        nome = registerUserDto.nome();
        email = registerUserDto.email();
        senha = registerUserDto.senha();
        role = registerUserDto.role();
    }

    public void UpdateDataUser (UpdateUsersDto updateUsersDto){
        if (updateUsersDto.id()!= null){
            id = updateUsersDto.id();
        }
        if (updateUsersDto.nome() != null){
            nome = updateUsersDto.nome();
        }
        if (updateUsersDto.email() != null){
            email = updateUsersDto.email();
        }
        if (updateUsersDto.senha()!= null){
            senha = updateUsersDto.senha();
        }
        if (updateUsersDto.role() != null){
            role = updateUsersDto.role();
        }

    }

}

