package com.genesis.amazonprofile.configuration;

public enum BucketName {
    USER("spring-user-storage");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
