package com.genesis.amazonprofile.service;

import com.genesis.amazonprofile.enums.Roles;
import com.genesis.amazonprofile.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UserService {

    User register(User user, MultipartFile file);
    byte[] downloadProfileImage(Long id);
    List<User> getAllUsers();
    List<User> getAllUsersByRole(Roles role);
}
