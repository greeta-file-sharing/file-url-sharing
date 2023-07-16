package io.swagger.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);
    Optional<User> findById(Long id);
    Optional<User> findBySessionId(String sessionId);
}
