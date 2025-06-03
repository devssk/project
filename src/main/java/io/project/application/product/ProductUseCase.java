package io.project.application.product;

import io.project.application.product.dto.ProductApplicationDto;
import io.project.application.product.dto.ProductApplicationDtoMapper;
import io.project.domain.product.dto.ProductDomainDto;
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
public class ProductUseCase {

    private final ProductService productService;
    private final ProductOptionService productOptionService;
    private final ProductOptionValueService productOptionValueService;
    private final ProductApplicationDtoMapper productApplicationDtoMapper;

    public ProductApplicationDto.InsertProductResult insertProduct(ProductApplicationDto.InsertProductCriteria criteria) {
        criteria.validate();
        log.info("application ProductUseCase insertProduct started payload: {}", criteria);
        List<ProductApplicationDto.InsertProductOptionCriteria> insertProductOptionCriteriaList = criteria.productOptionList();

        ProductDomainDto.InsertProductCommand insertProductCommand = productApplicationDtoMapper.toInsertProductCommand(criteria);

        ProductDomainDto.InsertProductInfo insertProductInfo = productService.insertProduct(insertProductCommand);

        List<ProductApplicationDto.InsertProductOptionResult> insertProductOptionResultList = insertProductOption(insertProductOptionCriteriaList, insertProductInfo.productId());

        log.info("application ProductUseCase insertProduct finished");
        return productApplicationDtoMapper.toInsertProductResult(insertProductInfo, insertProductOptionResultList);
    }

    public List<ProductApplicationDto.InsertProductOptionResult> insertProductOption(List<ProductApplicationDto.InsertProductOptionCriteria> insertProductOptionCriteriaList, long productId) {
        log.info("application ProductUseCase insertProductOption started payload: {}", insertProductOptionCriteriaList);
        List<ProductApplicationDto.InsertProductOptionResult> insertProductOptionResultList = new ArrayList<>();
        if (insertProductOptionCriteriaList != null && !insertProductOptionCriteriaList.isEmpty()) {
            for (ProductApplicationDto.InsertProductOptionCriteria insertProductOptionCriteria : insertProductOptionCriteriaList) {

                ProductOptionDomainDto.InsertProductOptionInfo insertProductOptionInfo = productOptionService.insertProductOption(productApplicationDtoMapper.toInsertProductOptionCommand(insertProductOptionCriteria, productId));

                List<ProductApplicationDto.InsertProductOptionValueResult> insertProductOptionValueResult = insertProductOptionValue(insertProductOptionCriteria.productOptionValueList(), insertProductOptionInfo.productOptionId());
                ProductApplicationDto.InsertProductOptionResult insertProductOptionResult = productApplicationDtoMapper.toInsertProductOptionResult(insertProductOptionInfo, insertProductOptionValueResult);

                insertProductOptionResultList.add(insertProductOptionResult);
            }
        }

        log.info("application ProductUseCase insertProductOption finished");
        return insertProductOptionResultList;
    }

    public List<ProductApplicationDto.InsertProductOptionValueResult> insertProductOptionValue(List<ProductApplicationDto.InsertProductOptionValueCriteria> insertProductOptionValueCriteriaList, long productOptionId) {
        log.info("application ProductUseCase insertProductOptionValue started payload: {}", insertProductOptionValueCriteriaList);
        List<ProductApplicationDto.InsertProductOptionValueResult> insertProductOptionValueResultList = new ArrayList<>();

        for (ProductApplicationDto.InsertProductOptionValueCriteria insertProductOptionValueCriteria : insertProductOptionValueCriteriaList) {
            ProductOptionValueDomainDto.InsertProductOptionValueInfo insertProductOptionValueInfo = productOptionValueService.insertProductOptionValue(productApplicationDtoMapper.toInsertProductOptionValueCommand(insertProductOptionValueCriteria, productOptionId));
            ProductApplicationDto.InsertProductOptionValueResult insertProductOptionValueResult = productApplicationDtoMapper.toInsertProductOptionValueResult(insertProductOptionValueInfo);
            insertProductOptionValueResultList.add(insertProductOptionValueResult);
        }

        log.info("application ProductUseCase insertProductOptionValue finished");
        return insertProductOptionValueResultList;
    }

    @Transactional(readOnly = true)
    public ProductApplicationDto.GetProductResult getProduct(ProductApplicationDto.GetProductCriteria criteria) {
        criteria.validate();
        log.info("application ProductUseCase getProduct started payload: {}", criteria);
        long productId = criteria.productId();

        if (!productService.existsProduct(productApplicationDtoMapper.toExistsProductCommand(productId))) {
            throw new IllegalArgumentException("application : product not found");
        }

        ProductDomainDto.GetProductInfo getProductInfo = productService.getProduct(productApplicationDtoMapper.toGetProductCommand(productId));

        List<ProductApplicationDto.GetProductOptionResult> productOptionList = getProductOption(getProductInfo);

        log.info("application ProductUseCase getProduct finished");
        return productApplicationDtoMapper.toGetProductResult(getProductInfo, productOptionList);
    }

    @Transactional(readOnly = true)
    public List<ProductApplicationDto.GetProductOptionResult> getProductOption(ProductDomainDto.GetProductInfo getProductInfo) {
        log.info("application ProductUseCase getProductOption started payload: {}", getProductInfo);
        List<ProductOptionDomainDto.GetProductOptionListInfo> getProductOptionList = productOptionService.getProductOptionList(productApplicationDtoMapper.toGetProductOptionListCommand(getProductInfo.productId()));
        List<ProductApplicationDto.GetProductOptionResult> resultList = new ArrayList<>();
        for (ProductOptionDomainDto.GetProductOptionListInfo getProductOptionListInfo : getProductOptionList) {
            List<ProductOptionValueDomainDto.GetProductOptionValueListInfo> productOptionValueList = getProductOptionValue(getProductOptionListInfo);
            resultList.add(productApplicationDtoMapper.toGetProductOptionResult(getProductOptionListInfo, productOptionValueList));
        }
        log.info("application ProductUseCase getProductOption finished");
        return resultList;
    }

    @Transactional(readOnly = true)
    public List<ProductOptionValueDomainDto.GetProductOptionValueListInfo> getProductOptionValue(ProductOptionDomainDto.GetProductOptionListInfo getProductOptionListInfo) {
        log.info("application ProductUseCase getProductOptionValue started payload: {}", getProductOptionListInfo);
        log.info("application ProductUseCase getProductOptionValue finished");
        return productOptionValueService.getProductOptionValueList(productApplicationDtoMapper.toGetProductOptionValueListCommand(getProductOptionListInfo.productOptionId()));
    }

    @Transactional(readOnly = true)
    public ProductApplicationDto.GetProductListResult getProductList(ProductApplicationDto.GetProductListCriteria criteria) {
        log.info("application ProductUseCase getProductList started payload: {}", criteria);
        ProductDomainDto.GetProductListInfo productList = productService.getProductList(productApplicationDtoMapper.toGetProductListCommand(criteria));

        List<ProductApplicationDto.GetProductList> list = productList.productList().stream().map(productApplicationDtoMapper::toGetProductList).toList();
        log.info("application ProductUseCase getProductList finished");
        return productApplicationDtoMapper.toGetProductListCriteria(productList, list);

    }

    public ProductApplicationDto.UpdateProductResult updateProduct(ProductApplicationDto.UpdateProductCriteria criteria) {
        criteria.validate();
        log.info("application ProductUseCase updateProduct started payload: {}", criteria);
        long productId = criteria.productId();

        if (!productService.existsProduct(productApplicationDtoMapper.toExistsProductCommand(productId))) {
            throw new IllegalArgumentException("application : product not found");
        }

        productService.updateProduct(productApplicationDtoMapper.toUpdateProductCommand(criteria));
        if (criteria.productOptionList() != null && !criteria.productOptionList().isEmpty()) {
            for (ProductApplicationDto.UpdateProductOptionCriteria updateProductOptionCriteria : criteria.productOptionList()) {
                productOptionService.updateProductOption(productApplicationDtoMapper.toUpdateProductOptionCommand(updateProductOptionCriteria));
                for (ProductApplicationDto.UpdateProductOptionValueCriteria updateProductOptionValueCriteria : updateProductOptionCriteria.productOptionValueList()) {
                    productOptionValueService.updateProductOptionValue(productApplicationDtoMapper.toUpdateProductOptionValueCommand(updateProductOptionValueCriteria));
                }
            }
        }

        log.info("application ProductUseCase updateProduct finished");
        return productApplicationDtoMapper.toUpdateProductResult(productId);
    }

    public void deleteProduct(ProductApplicationDto.DeleteProductCriteria criteria) {
        log.info("application ProductUseCase deleteProduct started payload: {}", criteria);
        ProductDomainDto.GetProductInfo getProductInfo = productService.getProduct(productApplicationDtoMapper.toGetProductCommand(criteria.productId()));
        List<ProductOptionDomainDto.GetProductOptionListInfo> productOptionList = productOptionService.getProductOptionList(productApplicationDtoMapper.toGetProductOptionListCommand(getProductInfo.productId()));
        for (ProductOptionDomainDto.GetProductOptionListInfo getProductOptionListInfo : productOptionList) {
            productOptionValueService.deleteProductOptionValueByProductOption(productApplicationDtoMapper.toDeleteProductOptionValueByProductOptionCommand(getProductOptionListInfo.productOptionId()));
        }
        for (ProductOptionDomainDto.GetProductOptionListInfo getProductOptionListInfo : productOptionList) {
            productOptionService.deleteProductOption(productApplicationDtoMapper.toDeleteProductOptionCommand(getProductOptionListInfo.productOptionId()));
        }
        productService.DeleteProduct(productApplicationDtoMapper.toDeleteProductCommand(criteria.productId()));
        log.info("application ProductUseCase deleteProduct finished");
    }


}
