package com.genesis.amazonprofile.service;

import com.genesis.amazonprofile.model.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {

    Profile register(Profile profile, MultipartFile file);
    byte[] downloadProfileImage(String id);
    List<Profile> getAllProfiles();
}
