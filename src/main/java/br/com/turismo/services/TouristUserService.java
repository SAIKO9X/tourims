package br.com.turismo.services;

import br.com.turismo.dto.AuthUserRequestDTO;
import br.com.turismo.entities.TouristUser;
import br.com.turismo.repositories.TouristUserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class TouristUserService {

  @Value("${security.token.secret.user}")
  private String secretKey;

  @Autowired
  private TouristUserRepository touristUserRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public TouristUser createUser(TouristUser user) {
    TouristUser existingUser = touristUserRepository.findByEmail(user.getEmail());

    if (existingUser != null) {
      throw new IllegalArgumentException("Já existe um usuário registrado com o e-mail fornecido");
    }

    var encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    return touristUserRepository.save(user);
  }

  public String authenticate(AuthUserRequestDTO authUserRequestDTO) {
    TouristUser touristUser = touristUserRepository.findByEmail(authUserRequestDTO.email());

    if (touristUser == null || !passwordEncoder.matches(authUserRequestDTO.password(), touristUser.getPassword())) {
      throw new UsernameNotFoundException("E-mail ou senha incorretos");
    }

    var roles = List.of("USER");

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

    return JWT.create().withIssuer("tourism").withSubject(touristUser.getId().toString()).withClaim("roles", roles).withExpiresAt(expiresIn).sign(algorithm);
  }

  public TouristUser findById(Long id) {
    return touristUserRepository.findById(id).orElse(null);
  }
}
