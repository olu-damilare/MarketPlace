package com.genesis.amazonprofile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesis.amazonprofile.model.Profile;
import org.springframework.stereotype.Component;


public class Util {
    public static Profile toProfile(String profile) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Profile userProfile = mapper.readValue(profile, Profile.class);
            return userProfile;
        } catch (JsonProcessingException e) {
            throw e;
        }
    }
}
