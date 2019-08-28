package com.altigee.needoff.users.data;

import com.altigee.needoff.users.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
  Optional<UserProfile> findByAccountId(Long accountId);
}
