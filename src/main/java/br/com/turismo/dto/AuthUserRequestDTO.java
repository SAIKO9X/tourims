package br.com.turismo.dto;

import lombok.Builder;

@Builder
public record AuthUserRequestDTO(String email, String password) {
}
