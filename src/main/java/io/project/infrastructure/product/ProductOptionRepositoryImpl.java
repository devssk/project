package io.project.infrastructure.product;

import io.project.domain.product.entity.ProductOption;
import io.project.domain.product.repository.ProductOptionRepository;
import io.project.infrastructure.product.jpa.ProductOptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class ProductOptionRepositoryImpl implements ProductOptionRepository {

    private final ProductOptionJpaRepository productOptionJpaRepository;

    @Override
    public ProductOption save(ProductOption productOption) {
        return productOptionJpaRepository.save(productOption);
    }

    @Override
    public ProductOption getProductOptionById(long id) {
        return productOptionJpaRepository.findByProductOptionIdAndDeleteYn(id, "N").orElseThrow(
                () -> new IllegalArgumentException("infrastructure : product option not found"));
    }

    @Override
    public List<ProductOption> getProductOptionsByProductId(long productId) {
        List<ProductOption> getProductOptionList = productOptionJpaRepository.findAllByProductIdAndDeleteYn(productId, "N");
        return getProductOptionList;
    }

    @Override
    public int countProductOptionsByProductId(long productId) {
        return productOptionJpaRepository.countProductOptionsByProductIdAndDeleteYn(productId, "N");
    }
}
