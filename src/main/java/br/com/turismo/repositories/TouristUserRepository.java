package br.com.turismo.repositories;

import br.com.turismo.entities.TouristUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristUserRepository extends JpaRepository<TouristUser, Long> {

    TouristUser findByEmail(String email);
}
