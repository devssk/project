package io.project.infrastructure.product.jpa;

import io.project.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIdAndDeleteYn(long productId, String deleteYn);
    Page<Product> findByDeleteYn(Pageable pageable, String deleteYn);
    boolean existsByProductIdAndDeleteYn(long productId, String deleteYn);
}
