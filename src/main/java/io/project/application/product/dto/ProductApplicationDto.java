package io.project.application.product.dto;

import io.project.domain.product.entity.ProductOptionType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public class ProductApplicationDto {

    public record InsertProductCriteria(
            String productName,
            String productDescription,
            Long productPrice,
            Integer deliveryCost,
            List<InsertProductOptionCriteria> productOptionList
    ) {
        public void validate() {
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("application : productName is required");
            }
            if (productDescription == null || productDescription.isEmpty()) {
                throw new IllegalArgumentException("application : productDescription is required");
            }
            if (productPrice == null || productPrice < 0) {
                throw new IllegalArgumentException("application : productPrice is required");
            }
            if (deliveryCost == null || deliveryCost < 0) {
                throw new IllegalArgumentException("application : deliveryCost is required");
            }
            if (productOptionList == null || productOptionList.isEmpty()) {
                return;
            }

            if (productOptionList.size() > 3) {
                throw new IllegalArgumentException("application : productOptionList.size() > 3");
            }

            for (InsertProductOptionCriteria insertProductOptionCriteria : productOptionList) {
                insertProductOptionCriteria.validate();
            }
        }
    }

    public record InsertProductOptionCriteria(
            String productOptionName,
            ProductOptionType productOptionType,
            List<InsertProductOptionValueCriteria> productOptionValueList
    ) {
        public void validate() {
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

    public record GetProductCriteria(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("application : productId is required");
            }
        }
    }

    public record GetProductListCriteria(
            Pageable pageable
    ) {
        public void validate() {
            if (pageable == null) {
                throw new IllegalArgumentException("application : pageable is required");
            }
        }
    }

    public record UpdateProductCriteria(
            Long productId,
            String productName,
            String productDescription,
            Long productPrice,
            Integer deliveryCost,
            List<UpdateProductOptionCriteria> productOptionList
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("application : productId is required");
            }
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("application : productName is required");
            }
            if (productDescription == null || productDescription.isEmpty()) {
                throw new IllegalArgumentException("application : productDescription is required");
            }
            if (productPrice == null || productPrice < 0) {
                throw new IllegalArgumentException("application : productPrice is required");
            }
            if (deliveryCost == null || deliveryCost < 0) {
                throw new IllegalArgumentException("application : productPrice is required");
            }
            if (productOptionList == null || productOptionList.isEmpty()) {
                return;
            }

            if (productOptionList.size() > 3) {
                throw new IllegalArgumentException("application : productOptionList.size() > 3");
            }

            for (UpdateProductOptionCriteria updateProductOptionCriteria : productOptionList) {
                updateProductOptionCriteria.validate();
            }
        }
    }

    public record UpdateProductOptionCriteria(
            Long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            List<UpdateProductOptionValueCriteria> productOptionValueList
    ) {
        public void validate() {
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("application : productOptionValueId is required");
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

    public record DeleteProductCriteria(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("application : productId is required");
            }
        }
    }

    public record GetProductListResult(
            long totalElements,
            int totalPages,
            int number,
            int size,
            List<GetProductList> productList
    ) {}

    public record GetProductList(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record InsertProductResult(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<InsertProductOptionResult> productOptionList
    ) {}

    public record InsertProductOptionResult(
            long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<InsertProductOptionValueResult> productOptionValueList
    ) {}

    public record InsertProductOptionValueResult(
            long productOptionValueId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record GetProductResult(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<GetProductOptionResult> productOptionList
    ) {}

    public record GetProductOptionResult(
            long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<GetProductOptionValueResult> productOptionValueList
    ) {}

    public record GetProductOptionValueResult(
            long productOptionValueId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record UpdateProductResult(
            Long productId
    ) {}


}
