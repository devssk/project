package io.project.domain.product.repository;

import io.project.domain.product.entity.ProductOptionValue;

import java.util.List;

public interface ProductOptionValueRepository {
    ProductOptionValue save(ProductOptionValue productOptionValue);
    ProductOptionValue getById(long id);
    List<ProductOptionValue> getProductOptionValuesByProductOptionId(long productOptionId);
}
