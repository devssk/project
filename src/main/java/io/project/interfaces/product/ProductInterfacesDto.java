package io.project.interfaces.product;

import io.project.application.product.dto.ProductOptionApplicationDto;
import io.project.domain.product.entity.ProductOptionType;

import java.time.LocalDateTime;
import java.util.List;

public class ProductInterfacesDto {

    public record InsertProductRequest(
            String productName,
            String productDescription,
            Long productPrice,
            Integer deliveryCost,
            List<InsertProductOptionRequest> productOptionList
    ) {
        public void validate() {
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productName is required");
            }
            if (productDescription == null || productDescription.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productDescription is required");
            }
            if (productPrice == null || productPrice < 0) {
                throw new IllegalArgumentException("interfaces : productPrice is required");
            }
            if (deliveryCost == null || deliveryCost < 0) {
                throw new IllegalArgumentException("interfaces : deliveryCost is required");
            }
            if (productOptionList == null || productOptionList.isEmpty()) {
                return;
            }
            for (InsertProductOptionRequest insertProductOptionRequest : productOptionList) {
                insertProductOptionRequest.validate();
            }
        }
    }

    public record InsertProductOptionRequest(
            String productOptionName,
            ProductOptionType productOptionType,
            List<InsertProductOptionValueRequest> productOptionValueList
    ) {
        public void validate() {
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionName is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("interfaces : productOptionType is required");
            }
            if (productOptionValueList == null || productOptionValueList.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionValueList is required");
            }
            for (InsertProductOptionValueRequest insertProductOptionValueRequest : productOptionValueList) {
                insertProductOptionValueRequest.validate();
            }
        }
    }

    public record InsertProductOptionValueRequest(
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionString is required");
            }
            if (productOptionCost == null || productOptionCost < 0) {
                throw new IllegalArgumentException("interfaces : productOptionCost is required");
            }
        }
    }

    public record UpdateProductRequest(
            Long productId,
            String productName,
            String productDescription,
            Long productPrice,
            Integer deliveryCost,
            List<UpdateProductOptionRequest> productOptionList
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("interfaces : productId is required");
            }
            if (productName == null || productName.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productName is required");
            }
            if (productDescription == null || productDescription.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productDescription is required");
            }
            if (productPrice == null || productPrice < 0) {
                throw new IllegalArgumentException("interfaces : productPrice is required");
            }
            if (deliveryCost == null || deliveryCost < 0) {
                throw new IllegalArgumentException("interfaces : productPrice is required");
            }
            if (productOptionList == null || productOptionList.isEmpty()) {
                return;
            }
            for (UpdateProductOptionRequest updateProductOptionRequest : productOptionList) {
                updateProductOptionRequest.validate();
            }
        }
    }

    public record UpdateProductOptionRequest(
            Long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            List<UpdateProductOptionValueRequest> productOptionValueList
    ) {
        public void validate() {
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("interfaces : productOptionId is required");
            }
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionName is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("interfaces : productOptionType is required");
            }
            if (productOptionValueList == null || productOptionValueList.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionValueList is required");
            }
            for (UpdateProductOptionValueRequest updateProductOptionValueRequest : productOptionValueList) {
                updateProductOptionValueRequest.validate();
            }
        }
    }

    public record UpdateProductOptionValueRequest(
            Long productOptionValueId,
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionValueId == null || productOptionValueId < 1) {
                throw new IllegalArgumentException("interfaces : productOptionValueId is required");
            }
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionString is required");
            }
            if (productOptionCost == null || productOptionCost < 0) {
                throw new IllegalArgumentException("interfaces : productOptionCost is required");
            }
        }
    }

    public record InsertOnlyProductOptionRequest(
            Long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            List<InsertOnlyProductOptionValueRequest> productOptionValueList
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("interfaces : productId is required");
            }
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionName is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("interfaces : productOptionType is required");
            }
            if (productOptionValueList == null || productOptionValueList.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionValueList is required");
            }
            for (InsertOnlyProductOptionValueRequest insertOnlyProductOptionValueRequest : productOptionValueList) {
                insertOnlyProductOptionValueRequest.validate();
            }
        }
    }

    public record InsertOnlyProductOptionValueRequest(
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionString is required");
            }
            if (productOptionCost == null || productOptionCost < 0) {
                throw new IllegalArgumentException("interfaces : productOptionCost is required");
            }
        }
    }

    public record UpdateOnlyProductOptionRequest(
            Long productId,
            Long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            List<UpdateOnlyProductOptionValueRequest> productOptionValueList
    ) {
        public void validate() {
            if (productId == null || productId < 1) {
                throw new IllegalArgumentException("interfaces : productId is required");
            }
            if (productOptionId == null || productOptionId < 1) {
                throw new IllegalArgumentException("interfaces : productOptionValueId is required");
            }
            if (productOptionName == null || productOptionName.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionName is required");
            }
            if (productOptionType == null) {
                throw new IllegalArgumentException("interfaces : productOptionType is required");
            }
            if (productOptionValueList == null || productOptionValueList.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionValueList is required");
            }
            for (UpdateOnlyProductOptionValueRequest updateOnlyProductOptionValueRequest : productOptionValueList) {
                updateOnlyProductOptionValueRequest.validate();
            }
        }
    }

    public record UpdateOnlyProductOptionValueRequest(
            Long productOptionValueId,
            String productOptionString,
            Integer productOptionCost
    ) {
        public void validate() {
            if (productOptionValueId == null || productOptionValueId < 1) {
                throw new IllegalArgumentException("interfaces : productOptionValueId is required");
            }
            if (productOptionString == null || productOptionString.isEmpty()) {
                throw new IllegalArgumentException("interfaces : productOptionString is required");
            }
            if (productOptionCost == null || productOptionCost < 0) {
                throw new IllegalArgumentException("interfaces : productOptionCost is required");
            }
        }
    }

    public record UpdateProductResponse(
            long productId
    ) {}

    public record InsertProductResponse(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            List<InsertProductOptionResponse> productOptionList
    ) {}

    public record InsertProductOptionResponse(
            long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            List<InsertProductOptionValueResponse> productOptionValueList
    ) {}

    public record InsertProductOptionValueResponse(
            long productOptionValueId,
            String productOptionString,
            int productOptionCost
    ) {}

    public record GetProductResponse(
            long productId,
            String productName,
            String productDescription,
            long productPrice,
            int deliveryCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<InsertProductOptionResponse> productOptionList
    ) {}

    public record GetProductOptionResponse(
            long productOptionId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<InsertProductOptionValueResponse> productOptionValueList
    ) {}

    public record GetProductOptionValueResponse(
            long productOptionValueId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record GetProductListResponse(
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

    public record InsertOnlyProductOptionResponse(
            long productOptionId,
            long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<InsertProductOptionValueResponse> productOptionValueList
    ) {}

    public record InsertOnlyProductOptionValueResponse(
            long productOptionValueId,
            long productOptionId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record GetOnlyProductOptionResponse(
            long productOptionId,
            long productId,
            String productOptionName,
            ProductOptionType productOptionType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<GetOnlyProductOptionValueResponse> productOptionValueList
    ) {}

    public record GetOnlyProductOptionValueResponse(
            long productOptionValueId,
            long productOptionId,
            String productOptionString,
            int productOptionCost,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}

    public record UpdateOnlyProductOptionResponse(
            long productOptionId
    ) {}

}
