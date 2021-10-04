package com.genesis.amazonprofile.service;

import com.genesis.amazonprofile.model.User;
import com.genesis.amazonprofile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.apache.http.entity.ContentType.*;


@Service
public class ProfileServiceImpl implements ProfileService{

    private final AppFileStore dataStore;
    private final UserRepository repository;

    public ProfileServiceImpl(@Qualifier("aws") AppFileStore dataStore, UserRepository repository) {
        this.dataStore = dataStore;
        this.repository = repository;
    }

    @Override
    public User register(User user, MultipartFile file) {
            if(file.isEmpty()){
                throw new IllegalStateException("Cannot upload empty file");
            }

          validateImageType(file);

           Map<String, String> values = dataStore.upload(file);
           String path = values.get("path");
           String fileName = values.get("fileName");

           user.setImagePath(path);
           user.setImageFileName(fileName);

            return repository.save(user);
    }



    private void validateImageType(MultipartFile file) {
        List<String> imageTypes = Arrays.asList(IMAGE_PNG.getMimeType(), IMAGE_BMP.getMimeType(),
                                               IMAGE_GIF.getMimeType(), IMAGE_JPEG.getMimeType());
        if(!imageTypes.contains(file.getContentType())){
            throw new IllegalStateException("File uploaded is not an image");
        }
    }

    @Override
    public byte[] downloadProfileImage(String id) {
        User user = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid user id"));
        return dataStore.download(user.getImagePath(), user.getImageFileName());
    }

    @Override
    public List<User> getAllProfiles() {
        return repository.findAll();
    }
}
