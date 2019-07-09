package com.altigee.needoff.users.data;

import com.altigee.needoff.users.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {
}
