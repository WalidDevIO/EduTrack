package fr.elite.authapi.repositories;

import fr.elite.authapi.entities.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends JpaRepository<Access, Long> {
    Optional<Access> findByUsername(String username);
    void deleteByUsername(String username);
}
