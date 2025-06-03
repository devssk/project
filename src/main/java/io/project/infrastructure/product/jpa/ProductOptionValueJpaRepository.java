package io.project.infrastructure.product.jpa;

import io.project.domain.product.entity.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOptionValueJpaRepository extends JpaRepository<ProductOptionValue, Long> {
    Optional<ProductOptionValue> findByProductOptionValueIdAndDeleteYn(long productOptionValueId, String deleteYn);
    List<ProductOptionValue> findAllByProductOptionIdAndDeleteYn(Long productOptionId, String deleteYn);
}
