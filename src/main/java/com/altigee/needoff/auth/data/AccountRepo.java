package com.altigee.needoff.auth.data;

import com.altigee.needoff.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
  Optional<Account> findByEmail(String email);
  boolean existsByEmail(String email);
}
