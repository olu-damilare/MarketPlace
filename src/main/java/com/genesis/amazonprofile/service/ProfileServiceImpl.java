package com.genesis.amazonprofile.service;

import com.genesis.amazonprofile.model.Profile;
import com.genesis.amazonprofile.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.apache.http.entity.ContentType.*;


@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final AppFileStore dataStore;
    private final ProfileRepository repository;

    @Override
    public Profile saveProfile(String firstName, String lastName, String email, MultipartFile file) {
            if(file.isEmpty()){
                throw new IllegalStateException("Cannot upload empty file");
            }

            validateImageType(file);

           Map<String, String> values = dataStore.upload(file);
           String path = values.get("path");
           String fileName = values.get("fileName");

            Profile profile = Profile.builder()
                                .firstName(firstName)
                                .lastName(lastName)
                                .email(email)
                                .imagePath(path)
                                .imageFileName(fileName)
                                .build();

            return repository.save(profile);
    }



    private void validateImageType(MultipartFile file) {
        List<String> imageTypes = Arrays.asList(IMAGE_PNG.getMimeType(), IMAGE_BMP.getMimeType(),
                                               IMAGE_GIF.getMimeType(), IMAGE_JPEG.getMimeType());
        if(!imageTypes.contains(file.getContentType())){
            throw new IllegalStateException("File uploaded is not an image");
        }
    }

    @Override
    public byte[] downloadProfileImage(Long id) {
        Profile profile = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid profile id"));
        return dataStore.download(profile.getImagePath(), profile.getImageFileName());
    }

    @Override
    public List<Profile> getAllProfiles() {
        return repository.findAll();
    }
}
