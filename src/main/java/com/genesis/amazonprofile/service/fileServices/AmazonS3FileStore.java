package com.genesis.amazonprofile.service.fileServices;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.genesis.amazonprofile.configuration.BucketName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service("aws")
public class AmazonS3FileStore implements AppFileStore{

    private final AmazonS3 amazonS3;


    @Override
    public Map<String, String> upload(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        Optional<Map<String, String>> optionalMetaData = Optional.of(metadata);
        String path = BucketName.USER.getBucketName() + "/" + UUID.randomUUID();
        String fileName = file.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if(!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });

        Map<String, String> response = new HashMap<>();
        response.put("path", path);
        response.put("fileName", fileName);

        try{
            amazonS3.putObject(path, fileName, file.getInputStream(), objectMetadata);
            return response;
        }catch(AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    @Override
    public byte[] download(String path, String key){
        try{
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        }catch(AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to download the file", e);
        }
    }
}
