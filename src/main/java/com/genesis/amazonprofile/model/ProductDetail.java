package com.genesis.amazonprofile.model;


import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ProductDetail {
    private String productName;
    private String desc;
    private String imagePath;
    private String imageFileName;
    private Long merchantId;

    public ProductDetail(@Nullable String productName, @Nullable String desc, @Nullable String imagePath, @Nullable String imageFileName, @Nullable Long merchantId) {
        this.productName = productName;
        this.desc = desc;
        this.imagePath = imagePath;
        this.imageFileName = imageFileName;
        this.merchantId = merchantId;
    }
}
