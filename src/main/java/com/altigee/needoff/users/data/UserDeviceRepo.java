package com.altigee.needoff.users.data;

import com.altigee.needoff.users.model.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeviceRepo extends JpaRepository<UserDevice, String> {
}
