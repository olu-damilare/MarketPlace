package com.genesis.amazonprofile.service.productService;

import com.genesis.amazonprofile.exceptions.InvalidDataException;
import com.genesis.amazonprofile.exceptions.InvalidIdException;
import com.genesis.amazonprofile.model.Product;
import com.genesis.amazonprofile.model.ProductDetail;
import com.genesis.amazonprofile.model.User;
import com.genesis.amazonprofile.repository.productRepository.ProductRepository;
import com.genesis.amazonprofile.repository.userRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public Product addProduct(Product product) {
        User merchant = userRepo.findById(product.getMerchantId()).orElseThrow(() -> new InvalidIdException("Invalid merchant id"));
        if(product.getProductName() == null || product.getProductName().length() < 1){
            throw new InvalidDataException("Product name cannot be empty");
        }
        if(product.getDesc() == null || product.getDesc().length() < 1){
            throw new InvalidDataException("Product description cannot be empty");
        }
        return productRepo.save(product);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepo.findById(productId).orElseThrow(() -> new InvalidIdException("Invalid product id"));
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepo.delete(product);
    }

    @Override
    public void UpdateProductDetail(ProductDetail details) {
        // TODO
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getAllProducts(Long merchantId) {
        return productRepo.getProductsByMerchantId(merchantId);
    }
}
