package com.genesis.amazonprofile.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryFileStore implements AppFileStore{

    @Autowired
    private Cloudinary cloudinary;


    @Override
    public Map<String, String> upload(MultipartFile file) {

        try {
            byte[] byteContent = file.getBytes();
            Map<?, ?> imageProperties = ObjectUtils.asMap("transformation", new Transformation()
                    .background("black")
                    .gravity("face")
                    .height(700)
                    .width(700)
                    .crop("fill")
                    .chain()
                    .opacity(50).chain());

            Map<Object, Object> uploadResponse = cloudinary.uploader().upload(byteContent, imageProperties);
            String path = (String) uploadResponse.get("url");
            String fileName = file.getOriginalFilename();

            Map<String, String> props = new HashMap<>();
            props.put("path", path);
            props.put("fileName", fileName);

            return props;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }


    }

    @Override
    public byte[] download(String path, String key) {
        try {
            URL imageUrl = new URL(path);
            return imageUrl.openStream().readAllBytes();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }
}
