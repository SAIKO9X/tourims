package br.com.turismo.dto;

import lombok.Builder;

@Builder
public record AssessmentRequestDTO(Integer pontuacao, String comentario, Long touristSpotId) {
}
