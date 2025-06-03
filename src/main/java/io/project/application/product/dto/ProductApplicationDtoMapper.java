package io.project.application.product.dto;

import io.project.domain.product.dto.ProductDomainDto;
import io.project.domain.product.dto.ProductOptionDomainDto;
import io.project.domain.product.dto.ProductOptionValueDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        implementationName = "ProductApplicationDtoMapperImpl"
)
public interface ProductApplicationDtoMapper {

    ProductDomainDto.InsertProductCommand toInsertProductCommand(ProductApplicationDto.InsertProductCriteria criteria);
    ProductDomainDto.UpdateProductCommand toUpdateProductCommand(ProductApplicationDto.UpdateProductCriteria criteria);
    ProductDomainDto.GetProductCommand toGetProductCommand(Long productId);
    ProductDomainDto.GetProductListCommand toGetProductListCommand(ProductApplicationDto.GetProductListCriteria criteria);
    ProductDomainDto.DeleteProductCommand toDeleteProductCommand(Long productId);
    ProductDomainDto.ExistsProductCommand toExistsProductCommand(Long productId);

    @Mapping(target = "productOptionList", source = "productOptionList")
    ProductApplicationDto.InsertProductResult toInsertProductResult(ProductDomainDto.InsertProductInfo info, List<ProductApplicationDto.InsertProductOptionResult> productOptionList);

    @Mapping(target = "productOptionValueList", source = "productOptionValueList")
    ProductApplicationDto.InsertProductOptionResult toInsertProductOptionResult(ProductOptionDomainDto.InsertProductOptionInfo info, List<ProductApplicationDto.InsertProductOptionValueResult> productOptionValueList);

    ProductApplicationDto.InsertProductOptionValueResult toInsertProductOptionValueResult(ProductOptionValueDomainDto.InsertProductOptionValueInfo info);

    @Mapping(target = "productOptionList", source = "productOptionList")
    ProductApplicationDto.GetProductResult toGetProductResult(ProductDomainDto.GetProductInfo info, List<ProductApplicationDto.GetProductOptionResult> productOptionList);

    ProductApplicationDto.GetProductList toGetProductList(ProductDomainDto.GetProductList list);

    @Mapping(target = "productList", source = "productList")
    ProductApplicationDto.GetProductListResult toGetProductListCriteria(ProductDomainDto.GetProductListInfo info, List<ProductApplicationDto.GetProductList> productList);

    @Mapping(target = "productOptionValueList", source = "productOptionValueList")
    ProductApplicationDto.GetProductOptionResult toGetProductOptionResult(ProductOptionDomainDto.GetProductOptionListInfo info, List<ProductOptionValueDomainDto.GetProductOptionValueListInfo> productOptionValueList);

    ProductApplicationDto.UpdateProductResult toUpdateProductResult(Long productId);

    @Mapping(target = "productId", source = "productId")
    ProductOptionDomainDto.InsertProductOptionCommand toInsertProductOptionCommand(ProductApplicationDto.InsertProductOptionCriteria criteria, long productId);
    ProductOptionDomainDto.GetProductOptionListCommand toGetProductOptionListCommand(Long productId);
    ProductOptionDomainDto.UpdateProductOptionCommand toUpdateProductOptionCommand(ProductApplicationDto.UpdateProductOptionCriteria criteria);
    ProductOptionDomainDto.DeleteProductOptionCommand toDeleteProductOptionCommand(Long productOptionId);

    @Mapping(target = "productOptionId", source = "productOptionId")
    ProductOptionValueDomainDto.InsertProductOptionValueCommand toInsertProductOptionValueCommand(ProductApplicationDto.InsertProductOptionValueCriteria criteria, long productOptionId);
    ProductOptionValueDomainDto.GetProductOptionValueListCommand toGetProductOptionValueListCommand(Long productOptionId);
    ProductOptionValueDomainDto.UpdateProductOptionValueCommand toUpdateProductOptionValueCommand(ProductApplicationDto.UpdateProductOptionValueCriteria criteria);
    ProductOptionValueDomainDto.DeleteProductOptionValueByProductOptionCommand toDeleteProductOptionValueByProductOptionCommand(Long productOptionId);

}
