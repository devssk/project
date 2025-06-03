package io.project.infrastructure.product.jpa;

import io.project.domain.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {
    Optional<ProductOption> findByProductOptionIdAndDeleteYn(long productOptionId, String deleteYn);
    List<ProductOption> findAllByProductIdAndDeleteYn(long productId, String deleteYn);
    int countProductOptionsByProductIdAndDeleteYn(long productId, String deleteYn);
}
