package com.altigee.needoff.users.service;

import com.altigee.needoff.users.data.UserDeviceRepo;
import com.altigee.needoff.users.model.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeviceServiceImpl implements UserDeviceService {

  @Autowired private UserDeviceRepo userDeviceRepo;

  @Override
  public UserDevice save(UserDevice userDevice) {
    return userDeviceRepo.save(userDevice);
  }

  @Override
  public Optional<UserDevice> delete(String id) {
    return userDeviceRepo.findById(id)
        .map(device -> {
          userDeviceRepo.delete(device);
          return device;
        });
  }
}
