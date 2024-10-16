package br.com.turismo.controller;

import br.com.turismo.dto.AuthUserRequestDTO;
import br.com.turismo.entities.TouristUser;
import br.com.turismo.services.TouristUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuário", description = "Operações relacionadas a usuários")
public class TouristUserController {

    @Autowired
    private TouristUserService touristUserService;

    @PostMapping("/")
    @Operation(summary = "Registrar usuário", description = "Registra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<TouristUser> registerUser(@RequestBody TouristUser user) {
        try {
            TouristUser createdUser = touristUserService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth")
    @Operation(summary = "Autenticar usuário", description = "Autentica um usuário e gera um token de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<String> authenticateUser(@RequestBody AuthUserRequestDTO authUserRequestDTO) {
        try {
            String token = touristUserService.authenticate(authUserRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
