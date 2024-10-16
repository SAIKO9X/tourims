package br.com.turismo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tourist_spot")
public class TouristSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do ponto turístico")
    private Long id;

    @NotEmpty(message = "O nome do ponto turístico não pode estar vazio")
    @Schema(description = "Nome do ponto turístico")
    private String nome;

    @Schema(description = "Categoria do ponto turístico")
    private String categoria;

    @NotEmpty(message = "A descrição do ponto turístico não pode estar vazia")
    @Schema(description = "Descrição do ponto turístico")
    private String descricao;

    @Schema(description = "Endereço do ponto turístico")
    private String endereco;

    @Schema(description = "Avaliação média do ponto turístico")
    private double avaliacaoMedia;

    @Schema(description = "Horário de funcionamento do ponto turístico")
    private String horarioFuncionamento;
}
