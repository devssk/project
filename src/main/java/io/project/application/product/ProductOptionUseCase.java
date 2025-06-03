package io.project.application.product;

import io.project.application.product.dto.ProductOptionApplicationDto;
import io.project.application.product.dto.ProductOptionApplicationDtoMapper;
import io.project.domain.product.dto.ProductOptionDomainDto;
import io.project.domain.product.dto.ProductOptionValueDomainDto;
import io.project.domain.product.service.ProductOptionService;
import io.project.domain.product.service.ProductOptionValueService;
import io.project.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductOptionUseCase {

    private final ProductService productService;
    private final ProductOptionService productOptionService;
    private final ProductOptionValueService productOptionValueService;
    private final ProductOptionApplicationDtoMapper dtoMapper;

    public ProductOptionApplicationDto.InsertProductOptionResult insertProductOption(ProductOptionApplicationDto.InsertProductOptionCriteria criteria) {
        criteria.validate();
        log.info("application ProductOptionUseCase insertProductOption started payload: {}", criteria);

        long productId = criteria.productId();

        if (!productService.existsProduct(dtoMapper.toExistsProductCommand(productId))) {
            throw new IllegalArgumentException("application : product not found");
        }

        int productOptionCount = productOptionService.countProductOption(dtoMapper.toCountProductOptionCommand(productId));

        if (productOptionCount == 3) {
            throw new IllegalArgumentException("application : productOption's size then less 3");
        }

        ProductOptionDomainDto.InsertProductOptionCommand insertProductOptionCommand = dtoMapper.toInsertProductOptionCommand(criteria);
        ProductOptionDomainDto.InsertProductOptionInfo insertProductOptionInfo = productOptionService.insertProductOption(insertProductOptionCommand);

        List<ProductOptionValueDomainDto.InsertProductOptionValueInfo> insertProductOptionValueInfoList = criteria.productOptionValueList()
                .stream()
                .map(
                        insertProductOptionValueCriteria ->
                                productOptionValueService.insertProductOptionValue(
                                        dtoMapper.toInsertProductOptionValueCommand(insertProductOptionValueCriteria,
                                                insertProductOptionInfo.productOptionId())
                                )
                )
                .toList();

        List<ProductOptionApplicationDto.InsertProductOptionValueResult> insertProductOptionValueResultList = insertProductOptionValueInfoList.stream().map(dtoMapper::toInsertProductOptionValueResult).toList();

        log.info("application ProductOptionUseCase insertProductOption finished");
        return dtoMapper.toInsertProductOptionResult(insertProductOptionInfo, insertProductOptionValueResultList);
    }

    @Transactional(readOnly = true)
    public List<ProductOptionApplicationDto.GetProductOptionResult> getProductOptionList(ProductOptionApplicationDto.GetProductOptionListCriteria criteria) {
        criteria.validate();
        log.info("application ProductOptionUseCase getProductOptionList started payload: {}", criteria);
        if (!productService.existsProduct(dtoMapper.toExistsProductCommand(criteria.productId()))) {
            throw new IllegalArgumentException("application : product not found");
        }

        List<ProductOptionApplicationDto.GetProductOptionResult> result = new ArrayList<>();
        List<ProductOptionDomainDto.GetProductOptionListInfo> productOptionList = productOptionService.getProductOptionList(new ProductOptionDomainDto.GetProductOptionListCommand(criteria.productId()));

        for (ProductOptionDomainDto.GetProductOptionListInfo getProductOptionListInfo : productOptionList) {
            List<ProductOptionValueDomainDto.GetProductOptionValueListInfo> productOptionValueList = productOptionValueService.getProductOptionValueList(dtoMapper.toGetProductOptionValueListCommand(criteria.productId()));
            List<ProductOptionApplicationDto.GetProductOptionValueResult> list = productOptionValueList.stream().map(dtoMapper::toGetProductOptionValueResult).toList();
            result.add(dtoMapper.toGetProductOptionResult(getProductOptionListInfo, list));
        }
        log.info("application ProductOptionUseCase getProductOptionList finished");
        return result;
    }

    public ProductOptionApplicationDto.UpdateProductOptionResult updateProductOptionList(ProductOptionApplicationDto.UpdateProductOptionCriteria criteria) {
        criteria.validate();
        log.info("application ProductOptionUseCase updateProductOptionList started payload: {}", criteria);
        long productOptionId = criteria.productId();

        if (!productService.existsProduct(dtoMapper.toExistsProductCommand(productOptionId))) {
            throw new IllegalArgumentException("application : product not found");
        }

        productOptionService.updateProductOption(dtoMapper.toUpdateProductOptionCommand(criteria));
        for (ProductOptionApplicationDto.UpdateProductOptionValueCriteria updateProductOptionValueCriterion : criteria.productOptionValueList()) {
            productOptionValueService.updateProductOptionValue(dtoMapper.toUpdateProductOptionValueCommand(updateProductOptionValueCriterion));
        }
        log.info("application ProductOptionUseCase updateProductOptionList finished");
        return new ProductOptionApplicationDto.UpdateProductOptionResult(productOptionId);
    }

    public void deleteProductOption(ProductOptionApplicationDto.DeleteProductOptionCriteria criteria) {
        criteria.validate();
        log.info("application ProductOptionUseCase deleteProductOption started payload: {}", criteria);
        long productId = criteria.productId();
        long productOptionId = criteria.productOptionId();

        if (!productService.existsProduct(dtoMapper.toExistsProductCommand(productId))) {
            throw new IllegalArgumentException("application : product not found");
        }

        productOptionValueService.deleteProductOptionValueByProductOption(dtoMapper.toDeleteProductOptionValueByProductOptionCommand(productOptionId));

        productOptionService.deleteProductOption(dtoMapper.toDeleteProductOptionCommand(productOptionId));
        log.info("application ProductOptionUseCase deleteProductOption finished");
    }

}
