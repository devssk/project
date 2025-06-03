package io.project.application.product.dto;

import io.project.domain.product.dto.ProductDomainDto;
import io.project.domain.product.dto.ProductOptionDomainDto;
import io.project.domain.product.dto.ProductOptionValueDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        implementationName = "ProductOptionApplicationDtoMapperImpl"
)
public interface ProductOptionApplicationDtoMapper {

    ProductDomainDto.ExistsProductCommand toExistsProductCommand(Long productId);

    ProductOptionDomainDto.InsertProductOptionCommand toInsertProductOptionCommand(ProductOptionApplicationDto.InsertProductOptionCriteria criteria);
    ProductOptionDomainDto.UpdateProductOptionCommand toUpdateProductOptionCommand(ProductOptionApplicationDto.UpdateProductOptionCriteria criteria);
    ProductOptionDomainDto.CountProductOptionCommand toCountProductOptionCommand(Long productId);
    ProductOptionDomainDto.DeleteProductOptionCommand toDeleteProductOptionCommand(Long productOptionId);

    @Mapping(target = "productOptionId", source = "productOptionId")
    ProductOptionValueDomainDto.InsertProductOptionValueCommand toInsertProductOptionValueCommand(ProductOptionApplicationDto.InsertProductOptionValueCriteria criteria, Long productOptionId);
    ProductOptionValueDomainDto.UpdateProductOptionValueCommand toUpdateProductOptionValueCommand(ProductOptionApplicationDto.UpdateProductOptionValueCriteria criteria);
    ProductOptionValueDomainDto.DeleteProductOptionValueByProductOptionCommand toDeleteProductOptionValueByProductOptionCommand(Long productOptionId);

    @Mapping(target = "productOptionValueList", source = "productOptionValueList")
    ProductOptionApplicationDto.InsertProductOptionResult toInsertProductOptionResult(ProductOptionDomainDto.InsertProductOptionInfo info, List<ProductOptionApplicationDto.InsertProductOptionValueResult> productOptionValueList);

    ProductOptionApplicationDto.InsertProductOptionValueResult toInsertProductOptionValueResult(ProductOptionValueDomainDto.InsertProductOptionValueInfo info);

    @Mapping(target = "productOptionValueList", source = "productOptionValueList")
    ProductOptionApplicationDto.GetProductOptionResult toGetProductOptionResult(ProductOptionDomainDto.GetProductOptionListInfo info, List<ProductOptionApplicationDto.GetProductOptionValueResult> productOptionValueList);
    ProductOptionApplicationDto.GetProductOptionValueResult toGetProductOptionValueResult(ProductOptionValueDomainDto.GetProductOptionValueListInfo info);

    ProductOptionValueDomainDto.GetProductOptionValueListCommand toGetProductOptionValueListCommand(Long productOptionId);
}
