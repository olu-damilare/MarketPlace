package com.genesis.amazonprofile.service.userService;

import com.genesis.amazonprofile.enums.Roles;
import com.genesis.amazonprofile.model.User;
import com.genesis.amazonprofile.repository.userRepository.UserRepository;
import com.genesis.amazonprofile.service.fileServices.AppFileStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static org.apache.http.entity.ContentType.*;


@Service
public class UserServiceImpl implements UserService {

    private final AppFileStore dataStore;
    private final UserRepository repository;

    public UserServiceImpl(@Qualifier("aws") AppFileStore dataStore, UserRepository repository) {
        this.dataStore = dataStore;
        this.repository = repository;
    }

    @Override
    public User register(User user, MultipartFile file) {
        if (file.isEmpty()) {
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
        if (!imageTypes.contains(file.getContentType())) {
            throw new IllegalStateException("File uploaded is not an image");
        }
    }

    @Override
    public byte[] downloadProfileImage(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new IllegalStateException("Invalid user id"));
        return dataStore.download(user.getImagePath(), user.getImageFileName());
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public List<User> getAllUsers(Roles role) {
        //TODO
        return null;
    }
}
