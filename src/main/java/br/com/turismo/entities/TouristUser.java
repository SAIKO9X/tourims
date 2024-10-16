package br.com.turismo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tourist_user")
public class TouristUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do usuário turístico")
    private Long id;

    @NotEmpty(message = "O campo de senha não pode estar vazio")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Schema(description = "Senha do usuário turístico")
    private String password;

    @NotEmpty(message = "O campo de e-mail não pode estar vazio")
    @Email(message = "O e-mail deve ser válido")
    @Schema(description = "E-mail do usuário turístico")
    private String email;
}
