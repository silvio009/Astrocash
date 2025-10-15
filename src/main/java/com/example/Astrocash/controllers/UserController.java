package com.example.Astrocash.controllers;

import com.example.Astrocash.dto.ChangePasswordDto;
import com.example.Astrocash.dto.user.*;
import com.example.Astrocash.models.users.Senha;
import com.example.Astrocash.models.users.User;
import com.example.Astrocash.repository.UserRepository;
import com.example.Astrocash.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // Habilita requisições de qualquer origem, útil para o frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping
    public ResponseEntity<List<ListingUsersDto>> ListingUsers (Pageable pageable){
        var page = userRepository.findAll(pageable).stream().map(ListingUsersDto :: new ).toList();
        return ResponseEntity.ok(page);

    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsUsersDto> ListingUsersId (@PathVariable("id") String id){
        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
        return  ResponseEntity.ok(new DetailsUsersDto(user));
    }

    @PostMapping
    public ResponseEntity<DetailsUsersDto> registerUser (@RequestBody @Valid RegisterUserDto registerUserDto, UriComponentsBuilder uriComponentsBuilder){
        var user = userService.cadastrarUsuario(registerUserDto);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUsersDto(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<DetailsUsersDto> UpdateDataUser (@PathVariable("id") String id,@RequestBody UpdateUsersDto updateUsersDto ){

        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado"));
        user.UpdateDataUser(updateUsersDto);
        userRepository.save(user);
        return ResponseEntity.ok(new DetailsUsersDto(user));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser (@PathVariable("id")String id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endereço do usuário
    @PutMapping("{id}/endereco")
    public ResponseEntity<DetailsUsersDto> updateEndereco(
            @PathVariable("id") String id,
            @RequestBody EnderecoDto enderecoDto
    ) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        user.setEndereco(enderecoDto.toEndereco());
        userRepository.save(user);

        return ResponseEntity.ok(new DetailsUsersDto(user));
    }

    // Foto usuário
    @PatchMapping("/foto")
    public ResponseEntity<User> atualizarFotoPerfil(
            @AuthenticationPrincipal User userLogado,
            @RequestBody UpdateUserPhotoDto dto) {

        User userAtualizado = userService.atualizarFotoPerfil(userLogado.getId(), dto);
        return ResponseEntity.ok(userAtualizado);
    }

    @PutMapping("/alterar-senha")
    public ResponseEntity<String> alterarSenha(
            @AuthenticationPrincipal User userLogado,
            @RequestBody ChangePasswordDto dto
    ) {
        if (userLogado == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado");
        }
        if (!passwordEncoder.matches(dto.senhaAtual(), userLogado.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha atual incorreta");
        }
        if (passwordEncoder.matches(dto.novaSenha(), userLogado.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nova senha deve ser diferente da atual");
        }
        if (!dto.novaSenha().equals(dto.confirmarSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As senhas não coincidem");
        }
        userLogado.setSenha(new Senha(passwordEncoder.encode(dto.novaSenha())));
        userRepository.save(userLogado);

        return ResponseEntity.ok("Senha alterada com sucesso");
    }


}
