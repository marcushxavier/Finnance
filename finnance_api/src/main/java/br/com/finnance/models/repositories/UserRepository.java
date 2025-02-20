package br.com.finnance.models.repositories;


import br.com.finnance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);

    UUID getIdByEmail(String email);
}
