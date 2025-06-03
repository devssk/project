package io.project.infrastructure.product;

import io.project.domain.product.entity.Product;
import io.project.domain.product.repository.ProductRepository;
import io.project.infrastructure.product.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productJpaRepository.findByProductIdAndDeleteYn(id, "N").orElseThrow(
                () -> new IllegalArgumentException("infrastructure : product not found")
        );
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productJpaRepository.findByDeleteYn(pageable, "N");
    }

    @Override
    public boolean existsProductById(long id) {
        return productJpaRepository.existsByProductIdAndDeleteYn(id, "N");
    }
}
