package br.com.turismo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private SecurityUserFilter securityUserFilter;

  private static final String[] PERMIT_ALL_LIST = {
    "/swagger-resources/**",
    "/swagger-ui/**",
    "/v3/api-docs/**",
    "/swagger-ui.html",
    "/webjars/**",
    "/actuator/**",
    "/public/**"
  };

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(crsf -> crsf.disable())
      .authorizeHttpRequests(auth -> {
        auth
          .requestMatchers("/user/").permitAll()
          .requestMatchers("/user/auth").permitAll()
          .requestMatchers(PERMIT_ALL_LIST).permitAll()
          .anyRequest().authenticated();
      })
      .addFilterBefore(securityUserFilter, BasicAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
