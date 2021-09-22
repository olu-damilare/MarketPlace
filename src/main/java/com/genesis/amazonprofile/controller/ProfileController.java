package com.genesis.amazonprofile.controller;


import com.genesis.amazonprofile.model.Profile;
import com.genesis.amazonprofile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles(){
      List<Profile> profiles = profileService.getAllProfiles();
      return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PostMapping(
            path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Profile> saveProfile(@RequestParam("firstName") String firstName,
                                                @RequestParam("lastName") String lastName,
                                                @RequestParam("email") String email,
                                               @RequestParam("file") MultipartFile file

    ){
        Profile profile = profileService.saveProfile(firstName, lastName, email, file);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping(value = "{id}/image/download")
    public byte[] downloadProfileImage(@PathVariable("id") Long id){
        return profileService.downloadProfileImage(id);
    }

}
