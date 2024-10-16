package br.com.turismo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da avaliação")
    private Long id;

    @NotNull(message = "A pontuação é obrigatória")
    @Min(value = 1, message = "A pontuação mínima é 1")
    @Max(value = 5, message = "A pontuação máxima é 5")
    @Schema(description = "Pontuação da avaliação", minimum = "1", maximum = "5")
    private Integer pontuacao;

    @NotEmpty(message = "O comentário não pode estar vazio")
    @Schema(description = "Comentário da avaliação")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "tourist_user_id", nullable = false)
    @Schema(description = "Usuário que fez a avaliação")
    private TouristUser user;

    @ManyToOne
    @JoinColumn(name = "tourist_spot_id", nullable = false)
    @Schema(description = "Local turístico avaliado")
    private TouristSpot spot;
}
