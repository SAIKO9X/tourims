package br.com.turismo.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTUserProvider {

  @Value("${security.token.secret.user}")
  private String secretKey;

  public String validateToken(String token) {
    token = token.replace("Bearer ", "");

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
      return jwt.getSubject();
    } catch (JWTVerificationException e) {
      throw new RuntimeException("Erro ao verificar token JWT: " + e.getMessage(), e);
    }
  }
}
