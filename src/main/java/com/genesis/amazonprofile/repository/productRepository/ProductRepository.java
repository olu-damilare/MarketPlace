package com.genesis.amazonprofile.repository.productRepository;

import com.genesis.amazonprofile.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductsByMerchantId(Long merchantId);
}
