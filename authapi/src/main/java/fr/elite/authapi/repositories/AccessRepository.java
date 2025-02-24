package fr.elite.authapi.repositories;

import fr.elite.authapi.entities.Access;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessRepository extends JpaRepository<Access, Long> {
    Optional<Access> findByUsername(String username);
}
