package com.boot.email.repository;

import com.boot.email.domains.Confirmation;
import com.boot.email.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository  extends JpaRepository<Confirmation,Long> {
    Confirmation findByToken(String token);
}
