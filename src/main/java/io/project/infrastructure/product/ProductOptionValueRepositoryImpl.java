package io.project.infrastructure.product;

import io.project.domain.product.entity.ProductOptionValue;
import io.project.domain.product.repository.ProductOptionValueRepository;
import io.project.infrastructure.product.jpa.ProductOptionValueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductOptionValueRepositoryImpl implements ProductOptionValueRepository {

    private final ProductOptionValueJpaRepository productOptionValueJpaRepository;

    @Override
    public ProductOptionValue save(ProductOptionValue productOptionValue) {
        return productOptionValueJpaRepository.save(productOptionValue);
    }

    @Override
    public ProductOptionValue getById(long id) {
        return productOptionValueJpaRepository.findByProductOptionValueIdAndDeleteYn(id, "N").orElseThrow(
                () -> new IllegalArgumentException("infrastructure : productOptionValue not found")
        );
    }

    @Override
    public List<ProductOptionValue> getProductOptionValuesByProductOptionId(long productOptionId) {
        return productOptionValueJpaRepository.findAllByProductOptionIdAndDeleteYn(productOptionId, "N");
    }
}
