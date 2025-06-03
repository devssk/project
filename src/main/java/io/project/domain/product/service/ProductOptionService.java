package io.project.domain.product.service;

import io.project.domain.product.dto.ProductOptionDomainDto;
import io.project.domain.product.entity.ProductOption;
import io.project.domain.product.entity.ProductOptionType;
import io.project.domain.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    public ProductOptionDomainDto.InsertProductOptionInfo insertProductOption(ProductOptionDomainDto.InsertProductOptionCommand command) {
        command.validate();
        log.info("domain ProductOptionService insertProductOption started payload: {}", command);
        long productId = command.productId();
        String productOptionName = command.productOptionName();
        ProductOptionType productOptionType = command.productOptionType();

        ProductOption productOption = new ProductOption(productId, productOptionName, productOptionType);

        productOptionRepository.save(productOption);

        log.info("domain ProductOptionService insertProductOption finished");
        return new ProductOptionDomainDto.InsertProductOptionInfo(
                productOption.getProductOptionId(),
                productOption.getProductId(),
                productOption.getProductOptionName(),
                productOption.getProductOptionType(),
                productOption.getCreatedAt(),
                productOption.getUpdatedAt()
        );
    }

    public ProductOptionDomainDto.GetProductOptionInfo getProductOption(ProductOptionDomainDto.GetProductOptionCommand command) {
        command.validate();
        log.info("domain ProductOptionService getProductOption started payload: {}", command);
        long productOptionId = command.productOptionId();
        ProductOption getProductOption = productOptionRepository.getProductOptionById(productOptionId);

        log.info("domain ProductOptionService getProductOption finished");
        return new ProductOptionDomainDto.GetProductOptionInfo(
                getProductOption.getProductOptionId(),
                getProductOption.getProductId(),
                getProductOption.getProductOptionName(),
                getProductOption.getProductOptionType(),
                getProductOption.getCreatedAt(),
                getProductOption.getUpdatedAt()
        );
    }

    public List<ProductOptionDomainDto.GetProductOptionListInfo> getProductOptionList(ProductOptionDomainDto.GetProductOptionListCommand command) {
        command.validate();
        log.info("domain ProductOptionService getProductOptionList started payload: {}", command);
        long productId = command.productId();
        List<ProductOption> getProductOptionList = productOptionRepository.getProductOptionsByProductId(productId);

        log.info("domain ProductOptionService getProductOptionList finished");
        return getProductOptionList.stream().map(productOption -> new ProductOptionDomainDto.GetProductOptionListInfo(
                productOption.getProductOptionId(),
                productId,
                productOption.getProductOptionName(),
                productOption.getProductOptionType(),
                productOption.getCreatedAt(),
                productOption.getUpdatedAt()
        )).toList();
    }

    public void updateProductOption(ProductOptionDomainDto.UpdateProductOptionCommand command) {
        command.validate();
        log.info("domain ProductOptionService updateProductOption started payload: {}", command);
        long productOptionId = command.productOptionId();
        String productOptionName = command.productOptionName();
        ProductOptionType productOptionType = command.productOptionType();

        ProductOption getProductOption = productOptionRepository.getProductOptionById(productOptionId);

        getProductOption.updateProductOptionName(productOptionName);
        getProductOption.updateProductOptionType(productOptionType);

        log.info("domain ProductOptionService updateProductOption finished");
    }

    public void deleteProductOption(ProductOptionDomainDto.DeleteProductOptionCommand command) {
        command.validate();
        log.info("domain ProductOptionService deleteProductOption started payload: {}", command);
        long productOptionId = command.productOptionId();

        ProductOption getProductOption = productOptionRepository.getProductOptionById(productOptionId);

        getProductOption.deleteProductOption();
        log.info("domain ProductOptionService deleteProductOption finished");
    }

    public void deleteProductOptionByProduct(ProductOptionDomainDto.DeleteProductOptionByProductCommand command) {
        command.validate();
        log.info("domain ProductOptionService deleteProductOptionByProduct started payload: {}", command);
        long productId = command.productId();

        List<ProductOption> getProductOptionList = productOptionRepository.getProductOptionsByProductId(productId);
        for (ProductOption productOption : getProductOptionList) {
            productOption.deleteProductOption();
        }
        log.info("domain ProductOptionService deleteProductOptionByProduct finished");
    }

    public int countProductOption(ProductOptionDomainDto.CountProductOptionCommand command) {
        command.validate();
        log.info("domain ProductOptionService countProductOption started payload: {}", command);
        long productId = command.productId();

        log.info("domain ProductOptionService countProductOption finished");
        return productOptionRepository.countProductOptionsByProductId(productId);
    }

}
