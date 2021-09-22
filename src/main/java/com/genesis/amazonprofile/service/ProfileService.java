package com.genesis.amazonprofile.service;

import com.genesis.amazonprofile.model.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {

    Profile saveProfile(String firstName, String lastName, String email, MultipartFile file);
    byte[] downloadProfileImage(Long id);
    List<Profile> getAllProfiles();
}
