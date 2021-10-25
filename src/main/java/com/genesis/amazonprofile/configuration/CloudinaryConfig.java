package com.genesis.amazonprofile.configuration;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration()
public class CloudinaryConfig {

    @Value("${cloudinary.cloud.name}")
    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloudinary.secret.key}")
    private String secretKey;

    @Bean
    public Cloudinary cloudinary() {
        log.info("-->{}", cloudName);
        log.info("-->{}", apiKey);
        log.info("-->{}", secretKey);
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", secretKey));

    }
}
