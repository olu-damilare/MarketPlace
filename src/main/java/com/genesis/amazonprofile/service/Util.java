package com.genesis.amazonprofile.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesis.amazonprofile.model.User;


public class Util {
    public static User toProfile(String user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(user, User.class);
    }
}
