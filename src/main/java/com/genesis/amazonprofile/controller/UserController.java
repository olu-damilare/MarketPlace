package com.genesis.amazonprofile.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.genesis.amazonprofile.model.User;
import com.genesis.amazonprofile.service.userService.UserService;
import com.genesis.amazonprofile.service.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/profiles/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllProfiles(){
      List<User> users = userService.getAllUsers();
      return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(
            path = "register",
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> register(@RequestPart("user") String user, @RequestPart("image") MultipartFile image
    )  {
        log.info("user ---> {}", user);
        User userDetails = null;
        try {
            userDetails = Util.toProfile(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("user ---> {}", userDetails);
        User registeredUser = userService.register(userDetails, image);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    @GetMapping(value = "{id}/image/download")
    public byte[] downloadProfileImage(@PathVariable("id") Long id){
        return userService.downloadProfileImage(id);
    }

}
