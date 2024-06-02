package com.boot.email.repository;

import com.boot.email.domains.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Id> {
    User findByEmailIgnoreCase(String email);

    Boolean existsByEmail(String email);
}
