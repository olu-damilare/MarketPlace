package com.genesis.amazonprofile.configuration;

public enum BucketName {
    PROFILE("spring-profile-storage");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
