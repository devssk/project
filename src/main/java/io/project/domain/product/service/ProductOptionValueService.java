package io.project.domain.product.service;

import io.project.domain.product.dto.ProductOptionValueDomainDto;
import io.project.domain.product.entity.ProductOptionValue;
import io.project.domain.product.repository.ProductOptionValueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductOptionValueService {

    private final ProductOptionValueRepository productOptionValueRepository;

    public ProductOptionValueDomainDto.InsertProductOptionValueInfo insertProductOptionValue(ProductOptionValueDomainDto.InsertProductOptionValueCommand command) {
        command.validate();
        log.info("domain ProductOptionValueService insertProductOptionValue stated payload: {}", command);
        long productOptionId = command.productOptionId();
        String productOptionString = command.productOptionString();
        int productOptionCost = command.productOptionCost();

        ProductOptionValue productOptionValue = new ProductOptionValue(productOptionId, productOptionString, productOptionCost);
        productOptionValueRepository.save(productOptionValue);

        log.info("domain ProductOptionValueService insertProductOptionValue finished");
        return new ProductOptionValueDomainDto.InsertProductOptionValueInfo(
                productOptionValue.getProductOptionValueId(),
                productOptionValue.getProductOptionId(),
                productOptionValue.getProductOptionString(),
                productOptionValue.getProductOptionCost(),
                productOptionValue.getCreatedAt(),
                productOptionValue.getUpdatedAt()
        );
    }

    public ProductOptionValueDomainDto.GetProductOptionValueInfo getProductOptionValue(ProductOptionValueDomainDto.GetProductOptionValueCommand command) {
        command.validate();
        log.info("domain ProductOptionValueService getProductOptionValue stated payload: {}", command);
        long productOptionValueId = command.productOptionValueId();

        ProductOptionValue getProductOptionValue = productOptionValueRepository.getById(productOptionValueId);

        log.info("domain ProductOptionValueService getProductOptionValue finished");
        return new ProductOptionValueDomainDto.GetProductOptionValueInfo(
                getProductOptionValue.getProductOptionValueId(),
                getProductOptionValue.getProductOptionId(),
                getProductOptionValue.getProductOptionString(),
                getProductOptionValue.getProductOptionCost(),
                getProductOptionValue.getCreatedAt(),
                getProductOptionValue.getUpdatedAt()
        );
    }

    public List<ProductOptionValueDomainDto.GetProductOptionValueListInfo> getProductOptionValueList(ProductOptionValueDomainDto.GetProductOptionValueListCommand command) {
        command.validate();
        log.info("domain ProductOptionValueService getProductOptionValueList stated payload: {}", command);
        long productOptionId = command.productOptionId();

        List<ProductOptionValue> getProductOptionValueList = productOptionValueRepository.getProductOptionValuesByProductOptionId(productOptionId);

        log.info("domain ProductOptionValueService getProductOptionValueList finished");
        return getProductOptionValueList.stream().map(productOptionValue -> new ProductOptionValueDomainDto.GetProductOptionValueListInfo(
                productOptionValue.getProductOptionValueId(),
                productOptionValue.getProductOptionId(),
                productOptionValue.getProductOptionString(),
                productOptionValue.getProductOptionCost(),
                productOptionValue.getCreatedAt(),
                productOptionValue.getUpdatedAt()
        )).toList();
    }

    public void updateProductOptionValue(ProductOptionValueDomainDto.UpdateProductOptionValueCommand command) {
        command.validate();
        log.info("domain ProductOptionValueService updateProductOptionValue stated payload: {}", command);
        long productOptionValueId = command.productOptionValueId();
        String productOptionString = command.productOptionString();
        int productOptionCost = command.productOptionCost();

        ProductOptionValue getProductOptionValue = productOptionValueRepository.getById(productOptionValueId);

        getProductOptionValue.updateProductOptionValue(productOptionString);
        getProductOptionValue.updateProductOptionCost(productOptionCost);

        log.info("domain ProductOptionValueService updateProductOptionValue finished");
    }

    public void deleteProductOptionValue(ProductOptionValueDomainDto.DeleteProductOptionValueCommand command) {
        command.validate();
        log.info("domain ProductOptionValueService deleteProductOptionValue stated payload: {}", command);
        long productOptionValueId = command.productOptionValueId();

        ProductOptionValue getProductOptionValue = productOptionValueRepository.getById(productOptionValueId);
        getProductOptionValue.deleteProductOptionValue();

        log.info("domain ProductOptionValueService deleteProductOptionValue finished");
    }

    public void deleteProductOptionValueByProductOption(ProductOptionValueDomainDto.DeleteProductOptionValueByProductOptionCommand command) {
        command.validate();
        log.info("domain ProductOptionValueService deleteProductOptionValueByProductOption stated payload: {}", command);
        long productOptionId = command.productOptionId();

        List<ProductOptionValue> getProductOptionValueList = productOptionValueRepository.getProductOptionValuesByProductOptionId(productOptionId);
        System.out.println(getProductOptionValueList);
        for (ProductOptionValue productOptionValue : getProductOptionValueList) {
            productOptionValue.deleteProductOptionValue();
            System.out.println(productOptionValue);
        }

        log.info("domain ProductOptionValueService deleteProductOptionValueByProductOption finished");
    }



}
