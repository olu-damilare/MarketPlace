package com.genesis.amazonprofile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public interface AppFileStore {

    Map<String, String> upload(MultipartFile file);

    byte[] download(String path, String key);
}
