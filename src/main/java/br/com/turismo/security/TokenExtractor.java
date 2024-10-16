package br.com.turismo.security;

import br.com.turismo.provider.JWTUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenExtractor {

  @Autowired
  private JWTUserProvider jwtUserProvider;

  public String extractUserIdFromToken(String authorizationHeader) {
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      return jwtUserProvider.validateToken(token);
    }
    return null;
  }
}
