package com.genesis.amazonprofile.service.userService;

import com.genesis.amazonprofile.enums.Roles;
import com.genesis.amazonprofile.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    User register(User user, MultipartFile file);

    byte[] downloadProfileImage(Long id);

    List<User> getAllUsers();

    List<User> getAllUsers(Roles role);
}
