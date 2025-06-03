package io.project.dto;

import io.project.application.product.dto.ProductApplicationDto;
import io.project.domain.product.entity.ProductOptionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationDtoTest {

    @Test
    @DisplayName("InsertProductCriteria 유효성 검사 - 성공")
    public void productApplicationTest01() {
        // given
        List<ProductApplicationDto.InsertProductOptionValueCriteria> insertProductOptionValueCriteriaList = new ArrayList<>();
        String productOptionString = "상품 옵션 값";
        Integer productOptionCost = 0;
        ProductApplicationDto.InsertProductOptionValueCriteria productOptionValueCriteria = new ProductApplicationDto.InsertProductOptionValueCriteria(productOptionString, productOptionCost);

        insertProductOptionValueCriteriaList.add(productOptionValueCriteria);

        List<ProductApplicationDto.InsertProductOptionCriteria> insertProductOptionCriteriaList = new ArrayList<>();
        String productOptionName = "상품 옵션 이름";
        ProductOptionType productOptionType = ProductOptionType.CUSTOM;
        ProductApplicationDto.InsertProductOptionCriteria productOptionCriteria = new ProductApplicationDto.InsertProductOptionCriteria(productOptionName, productOptionType, insertProductOptionValueCriteriaList);

        insertProductOptionCriteriaList.add(productOptionCriteria);

        String productName = "상품 이름";
        String productDescription = "상품 설명";
        long productPrice = 0;
        int deliveryCost = 0;
        ProductApplicationDto.InsertProductCriteria productCriteria = new ProductApplicationDto.InsertProductCriteria(productName, productDescription, productPrice, deliveryCost, insertProductOptionCriteriaList);

        // when
        productCriteria.validate();

        // then
        assertEquals(productName, productCriteria.productName());
        assertEquals(productDescription, productCriteria.productDescription());
        assertEquals(productPrice, productCriteria.productPrice());
        assertEquals(deliveryCost, productCriteria.deliveryCost());
    }

}
