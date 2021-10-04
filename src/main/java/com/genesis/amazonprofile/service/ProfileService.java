package com.genesis.amazonprofile.service;

import com.genesis.amazonprofile.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {

    User register(User user, MultipartFile file);
    byte[] downloadProfileImage(String id);
    List<User> getAllProfiles();
}
