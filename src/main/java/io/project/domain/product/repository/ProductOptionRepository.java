package io.project.domain.product.repository;

import io.project.domain.product.entity.ProductOption;

import java.util.List;

public interface ProductOptionRepository {

    ProductOption save(ProductOption productOption);
    ProductOption getProductOptionById(long id);
    List<ProductOption> getProductOptionsByProductId(long productId);
    int countProductOptionsByProductId(long productId);

}
