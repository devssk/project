package io.project.domain.product.dto;

import java.time.LocalDateTime;

public class ProductOptionValueDomainDto {

    public record InsertProductOptionValueCommand(
            Long productOptionId,
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("domain : productOptionValueId is required");
            }
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("domain : productOptionString is required");
            }
            if (productOptionCost == null || productOptionCost < 1) {
                throw new IllegalArgumentException("domain : productOptionCost is required");
            }
        }
    }

    public record GetProductOptionValueCommand(
            Long productOptionValueId,
            Long productOptionId
    ) {
        public void validate() {
            if (productOptionValueId == null || productOptionValueId < 1) {
                throw new IllegalArgumentException("domain : productOptionValueId is required");
            }
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("domain : productOptionValueId is required");
            }
        }
    }

    public record GetProductOptionValueListCommand(
            Long productOptionId
    ) {
        public void validate() {
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("domain : productOptionValueId is required");
            }
        }
    }

    public record UpdateProductOptionValueCommand(
            Long productOptionValueId,
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionValueId == null) {
                throw new IllegalArgumentException("productOptionValueId is null");
            }
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("productOptionString is null or empty");
            }
            if (productOptionCost == null || productOptionCost < 0) {
                throw new IllegalArgumentException("productOptionCost is null");
            }
        }
    }

    public record DeleteProductOptionValueCommand(
            Long productOptionValueId
    ) {
        public void validate() {
            if (productOptionValueId == null || productOptionValueId < 1) {
                throw new IllegalArgumentException("productOptionValueId is required");
            }
        }
    }

    public record DeleteProductOptionValueByProductOptionCommand(
            Long productOptionId
    ) {
        public void validate() {
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("productOptionValueId is required");
            }
        }
    }

    public record InsertProductOptionValueInfo(
            long productOptionId,
            long productOptionValueId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}


    public record GetProductOptionValueInfo(
            long productOptionValueId,
            long productOptionId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record GetProductOptionValueListInfo(
            long productOptionValueId,
            long productOptionId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

}
