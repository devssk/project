package io.project.domain.product.dto;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDomainDto {

    public record InsertProductCommand(
            String productName,
            String productDescription,
            Long productPrice,
            Integer deliveryCost
    ) {
        public void validate() {
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("domain : productName is required");
            }
            if (productDescription == null || productDescription.isEmpty()) {
                throw new IllegalArgumentException("domain : productDescription is required");
            }
            if (productPrice == null || productPrice < 0) {
                throw new IllegalArgumentException("domain : productPrice is required");
            }
            if (deliveryCost == null || deliveryCost < 0) {
                throw new IllegalArgumentException("domain : deliveryCost is required");
            }
        }
    }

    public record GetProductCommand(
            Long productId
    ) {
        public void validate() {
            if (productId == null) {
                throw new IllegalArgumentException("domain : productId is required");
            }
        }
    }

    public record GetProductListCommand(
            Pageable pageable
    ) {
        public void validate() {
            if (pageable == null) {
                throw new IllegalArgumentException("domain : pageable is required");
            }
        }
    }


    public record UpdateProductCommand(
            Long productId,
            String productName,
            String productDescription,
            Long productPrice,
            Integer deliveryCost
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("domain : productId is required");
            }
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("domain : productName is required");
            }
            if (productDescription == null || productDescription.isEmpty()) {
                throw new IllegalArgumentException("domain : productDescription is required");
            }
            if (productPrice == null || productPrice < 0) {
                throw new IllegalArgumentException("domain : productPrice is required");
            }
            if (deliveryCost == null || deliveryCost < 0) {
                throw new IllegalArgumentException("domain : deliveryCost is required");
            }
        }
    }

    public record DeleteProductCommand(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("domain : productId is required");
            }
        }
    }

    public record ExistsProductCommand(
            Long productId
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("domain : productId is required");
            }
        }
    }

    public record InsertProductInfo(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record GetProductInfo(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}


    public record GetProductListInfo(
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

    public record UpdateProductInfo(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

}
