package com.genesis.amazonprofile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesis.amazonprofile.model.User;


public class Util {
    public static User toProfile(String user) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            User userProfile = mapper.readValue(user, User.class);
            return userProfile;
        } catch (JsonProcessingException e) {
            throw e;
        }
    }
}
