package io.project.application.product.dto;

import io.project.domain.product.entity.ProductOptionType;

import java.time.LocalDateTime;
import java.util.List;

public class ProductOptionApplicationDto {

    public record InsertProductOptionCriteria(
            Long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            List<InsertProductOptionValueCriteria> productOptionValueList
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("application : productId is required");
            }
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("application : productOptionName is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("application : productOptionType is required");
            }
            if (productOptionType == ProductOptionType.CUSTOM && productOptionValueList != null && productOptionValueList.size() > 1) {
                throw new IllegalArgumentException("application : CUSTOM type is only 1");
            }
            if (productOptionValueList == null || productOptionValueList.isEmpty()) {
                throw new IllegalArgumentException("application : productOptionValueList is required");
            }
            for (InsertProductOptionValueCriteria insertProductOptionValueCriteria : productOptionValueList) {
                insertProductOptionValueCriteria.validate();
            }
        }
    }

    public record InsertProductOptionValueCriteria(
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("application : productOptionString is required");
            }
            if (productOptionCost == null || productOptionCost < 0) {
                throw new IllegalArgumentException("application : productOptionCost is required");
            }
        }
    }

    public record UpdateProductOptionCriteria(
            Long productId,
            Long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            List<UpdateProductOptionValueCriteria> productOptionValueList
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("application : productId is required");
            }
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("application : productOptionValueId is required");
            }
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("application : productOptionName is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("application : productOptionType is required");
            }
            if (productOptionValueList == null || productOptionValueList.isEmpty()) {
                throw new IllegalArgumentException("application : productOptionValueList is required");
            }
            for (UpdateProductOptionValueCriteria updateProductOptionValueCriteria : productOptionValueList) {
                updateProductOptionValueCriteria.validate();
            }
        }
    }

    public record UpdateProductOptionValueCriteria(
            Long productOptionValueId,
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionValueId == null || productOptionValueId < 1) {
                throw new IllegalArgumentException("application : productOptionValueId is required");
            }
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("application : productOptionString is required");
            }
            if (productOptionCost == null || productOptionCost < 0) {
                throw new IllegalArgumentException("application : productOptionCost is required");
            }
        }
    }

    public record GetProductOptionListCriteria(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("application : productId is required");
            }
        }
    }

    public record DeleteProductOptionCriteria(
            Long productId,
            Long productOptionId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("application : productId is required");
            }
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("application : productOptionValueId is required");
            }
        }
    }

    public record GetProductOptionResult(
            long productOptionId,
            long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<GetProductOptionValueResult> productOptionValueList
    ) {}

    public record GetProductOptionValueResult(
            long productOptionValueId,
            long productOptionId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record InsertProductOptionResult(
            long productOptionId,
            long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<InsertProductOptionValueResult> productOptionValueList
    ) {}

    public record InsertProductOptionValueResult(
            long productOptionValueId,
            long productOptionId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record UpdateProductOptionResult(
            long productOptionId
    ) {}

}
