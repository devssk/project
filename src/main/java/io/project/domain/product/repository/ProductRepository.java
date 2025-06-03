package io.project.domain.product.repository;

import io.project.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);
    Product getProductById(Long id);
    Page<Product> getAllProducts(Pageable pageable);
    boolean existsProductById(long id);

}
