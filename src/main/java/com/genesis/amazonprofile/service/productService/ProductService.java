package com.genesis.amazonprofile.service.productService;

import com.genesis.amazonprofile.model.Product;
import com.genesis.amazonprofile.model.ProductDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product addProduct(Product product);
    Product getProduct(Long productId);
    void deleteProduct(Long productId);
    void UpdateProductDetail(ProductDetail details);
    List<Product> getAllProducts();
    List<Product> getAllProducts(Long merchantId);
}
