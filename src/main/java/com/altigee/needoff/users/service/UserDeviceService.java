package com.altigee.needoff.users.service;

import com.altigee.needoff.users.model.UserDevice;

import java.util.Optional;

public interface UserDeviceService{
    UserDevice save(UserDevice userDevice);
    Optional<UserDevice> delete(String id);
}
