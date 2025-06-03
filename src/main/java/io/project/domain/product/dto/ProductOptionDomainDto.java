package io.project.domain.product.dto;

import io.project.domain.product.entity.ProductOptionType;

import java.time.LocalDateTime;

public class ProductOptionDomainDto {

    public record InsertProductOptionCommand(
            Long productId,
            String productOptionName,
            ProductOptionType productOptionType
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("domain : productId is required");
            }
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("domain : productOptionName is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("domain : productOptionType is required");
            }
        }
    }

    public record GetProductOptionCommand(
            Long productOptionId
    ) {
        public void validate() {
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("domain : productOptionValueId is required");
            }
        }
    }

    public record GetProductOptionListCommand(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("domain : productId is required");
            }
        }
    }

    public record UpdateProductOptionCommand(
            Long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType
    ) {
        public void validate() {
            if (productOptionId == null) {
                throw new IllegalArgumentException("domain : Product option id is required");
            }
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("domain : Product option name is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("domain : Product option type is required");
            }
        }
    }

    public record DeleteProductOptionCommand(
            Long productOptionId
    ) {
        public void validate() {
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("domain : Product option id is required");
            }
        }
    }

    public record DeleteProductOptionByProductCommand(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("domain : Product option id is required");
            }
        }
    }

    public record CountProductOptionCommand(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("domain : Product option id is required");
            }
        }
    }

    public record InsertProductOptionInfo(
            long productOptionId,
            long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record GetProductOptionInfo(
            long productOptionId,
            long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}


    public record GetProductOptionListInfo(
            long productOptionId,
            long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}


    public record CountProductOptionInfo(
            int count
    ) {}

}
