package io.project.domain.product.service;

import io.project.domain.product.dto.ProductDomainDto;
import io.project.domain.product.entity.Product;
import io.project.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDomainDto.InsertProductInfo insertProduct(ProductDomainDto.InsertProductCommand command) {
        command.validate();
        log.info("domain ProductService insertProduct started payload: {}", command);
        String productName = command.productName();
        String productDescription = command.productDescription();
        long productPrice = command.productPrice();
        int deliveryCost = command.deliveryCost();

        Product product = new Product(productName, productDescription, productPrice, deliveryCost);

        Product saveProduct = productRepository.save(product);

        log.info("domain ProductService insertProduct finished");
        return new ProductDomainDto.InsertProductInfo(
                saveProduct.getProductId(),
                saveProduct.getProductName(),
                saveProduct.getProductDescription(),
                saveProduct.getProductPrice(),
                saveProduct.getDeliveryCost(),
                saveProduct.getCreatedAt(),
                saveProduct.getUpdatedAt()
        );
    }

    public ProductDomainDto.GetProductInfo getProduct(ProductDomainDto.GetProductCommand command) {
        command.validate();
        log.info("domain ProductService getProduct started payload: {}", command);
        long productId = command.productId();

        Product getProduct = productRepository.getProductById(productId);

        log.info("domain ProductService getProduct finished");
        return new ProductDomainDto.GetProductInfo(
                getProduct.getProductId(),
                getProduct.getProductName(),
                getProduct.getProductDescription(),
                getProduct.getProductPrice(),
                getProduct.getDeliveryCost(),
                getProduct.getCreatedAt(),
                getProduct.getUpdatedAt()
        );
    }

    public ProductDomainDto.GetProductListInfo getProductList(ProductDomainDto.GetProductListCommand command) {
        command.validate();
        log.info("domain ProductService getProductList started payload: {}", command);
        Page<Product> productList = productRepository.getAllProducts(command.pageable());

        List<ProductDomainDto.GetProductList> list = productList.getContent().stream().map(product -> new ProductDomainDto.GetProductList(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getDeliveryCost(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        )).toList();

        log.info("domain ProductService getProductList finished");
        return new ProductDomainDto.GetProductListInfo(productList.getTotalElements(), productList.getTotalPages(), productList.getNumber() + 1, productList.getSize(), list);

    }

    public ProductDomainDto.UpdateProductInfo updateProduct(ProductDomainDto.UpdateProductCommand command) {
        command.validate();
        log.info("domain ProductService updateProduct started payload: {}", command);
        long productId = command.productId();
        String productName = command.productName();
        String productDescription = command.productDescription();
        long productPrice = command.productPrice();
        int deliveryCost = command.deliveryCost();

        Product product = productRepository.getProductById(productId);
        product.updateProductName(productName);
        product.updateProductDescription(productDescription);
        product.updateProductPrice(productPrice);
        product.updateDeliveryCost(deliveryCost);

        log.info("domain ProductService updateProduct finished");
        return new ProductDomainDto.UpdateProductInfo(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getDeliveryCost(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public void DeleteProduct(ProductDomainDto.DeleteProductCommand command) {
        command.validate();
        log.info("domain ProductService deleteProduct started payload: {}", command);
        long productId = command.productId();
        Product getProduct = productRepository.getProductById(productId);
        getProduct.deleteProduct();

        log.info("domain ProductService deleteProduct finished");
    }

    public boolean existsProduct(ProductDomainDto.ExistsProductCommand command) {
        command.validate();
        log.info("domain ProductService existsProduct started payload: {}", command);
        log.info("domain ProductService existsProduct finished");
        return productRepository.existsProductById(command.productId());
    }

}
