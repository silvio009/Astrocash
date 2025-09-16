package com.example.Astrocash.models.users;




import com.example.Astrocash.dto.user.RegisterUserDto;
import com.example.Astrocash.dto.user.UpdateUsersDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@ToString
public class User implements UserDetails {


    @Id
    private String id;

    private String nome;

    private Email email;

    private Senha senha;

    private String cpf;

    private Date dataCadastro;

    private String telefone;

    private String role = "USER";

    public User(RegisterUserDto registerUserDto) {
        nome = registerUserDto.nome();
        email = registerUserDto.email();
        senha = registerUserDto.senha();
        role = registerUserDto.role();
        cpf = registerUserDto.cpf();
        dataCadastro = new Date();
        telefone = registerUserDto.telefone();
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
        if (updateUsersDto.cpf()!= null){
            cpf = updateUsersDto.cpf();
        }
        if (updateUsersDto.telefone() != null){
            telefone = updateUsersDto.telefone();
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + role.toUpperCase());
    }

    @Override
    public String getPassword() {
        return senha.getValue();
    }

    @Override
    public String getUsername() {
        return email.getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

