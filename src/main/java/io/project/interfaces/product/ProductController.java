package io.project.interfaces.product;

import io.project.application.product.ProductOptionUseCase;
import io.project.application.product.dto.ProductApplicationDto;
import io.project.application.product.ProductUseCase;
import io.project.application.product.dto.ProductOptionApplicationDto;
import io.project.interfaces.ResponseCode;
import io.project.interfaces.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductOptionUseCase productOptionUseCase;
    private final ProductInterfacesDtoMapper mapper;

    @PostMapping("")
    public ResponseEntity<ResponseDto> insertProduct(@RequestBody ProductInterfacesDto.InsertProductRequest request) {
        request.validate();
        log.info("interfaces ProductController [POST] /products payload: {}", request);

        ProductApplicationDto.InsertProductCriteria insertProductCriteria = mapper.toInsertProductCriteria(request);

        ProductApplicationDto.InsertProductResult result = productUseCase.insertProduct(insertProductCriteria);

        ProductInterfacesDto.InsertProductResponse response = mapper.toInsertProductResponse(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto> getProduct(@PathVariable(name = "productId") long productId) {
        log.info("interfaces ProductController [GET] /products/{}", productId);
        ProductApplicationDto.GetProductResult result = productUseCase.getProduct(new ProductApplicationDto.GetProductCriteria(productId));

        ProductInterfacesDto.GetProductResponse response = mapper.toGetProductResponse(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

    @GetMapping("")
    public ResponseEntity<ResponseDto> getProductList(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        log.info("interfaces ProductController [GET] /products?page={}&size={}", page, size);
        Pageable pageable = PageRequest.of(page - 1, size);
        ProductApplicationDto.GetProductListResult result = productUseCase.getProductList(mapper.toGetProductListCriteria(pageable));

        ProductInterfacesDto.GetProductListResponse response = mapper.toGetProductListResult(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto> updateProduct(@RequestBody ProductInterfacesDto.UpdateProductRequest request) {
        log.info("interfaces ProductController [PATCH] /products payload: {}", request);

        ProductApplicationDto.UpdateProductCriteria updateProductCriteria = mapper.toUpdateProductCriteria(request);

        ProductApplicationDto.UpdateProductResult result = productUseCase.updateProduct(updateProductCriteria);

        ProductInterfacesDto.UpdateProductResponse response = mapper.toUpdateProductResult(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable(name = "productId") long productId) {
        log.info("interfaces ProductController [DELETE] /products/{}", productId);
        productUseCase.deleteProduct(mapper.toDeleteProductCriteria(productId));
        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, null));
    }

    @PostMapping("/productOption")
    public ResponseEntity<ResponseDto> insertProductOption(@RequestBody ProductInterfacesDto.InsertOnlyProductOptionRequest request) {
        log.info("interfaces ProductController [POST] /productOptions payload: {}", request);
        ProductOptionApplicationDto.InsertProductOptionCriteria insertOnlyProductOptionCriteria = mapper.toInsertOnlyProductOptionCriteria(request);

        ProductOptionApplicationDto.InsertProductOptionResult result = productOptionUseCase.insertProductOption(insertOnlyProductOptionCriteria);

        ProductInterfacesDto.InsertOnlyProductOptionResponse response = mapper.toInsertOnlyProductOptionResponse(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

    @GetMapping("/{productId}/productOption")
    public ResponseEntity<ResponseDto> getProductOption(@PathVariable(name = "productId") long productId) {
        log.info("interfaces ProductController [GET] /products/{}/productOption", productId);

        List<ProductOptionApplicationDto.GetProductOptionResult> result = productOptionUseCase.getProductOptionList(mapper.toGetProductOptionListCriteria(productId));

        List<ProductInterfacesDto.GetOnlyProductOptionResponse> response = result.stream().map(mapper::toGetOnlyProductOptionResponse).toList();

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

    @PatchMapping("/productOption")
    public ResponseEntity<ResponseDto> updateProductOption(@RequestBody ProductInterfacesDto.UpdateOnlyProductOptionRequest request) {
        log.info("interfaces ProductController [PATCH] /productOptions payload: {}", request);

        ProductOptionApplicationDto.UpdateProductOptionResult result = productOptionUseCase.updateProductOptionList(mapper.toUpdateProductOptionCriteria(request));

        ProductInterfacesDto.UpdateOnlyProductOptionResponse response = mapper.toUpdateOnlyProductOptionResponse(result);

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, response));
    }

    @DeleteMapping("/{productId}/productOption/{productOptionId}")
    public ResponseEntity<ResponseDto> deleteProductOption(@PathVariable(name = "productId") long productId, @PathVariable(name = "productOptionId") long productOptionId) {
        log.info("interfaces ProductController [DELETE] /products/{}/productOption/{}", productId, productOptionId);
        productOptionUseCase.deleteProductOption(mapper.toDeleteProductOptionCriteria(productId, productOptionId));

        return ResponseEntity.ok(new ResponseDto(ResponseCode.SUCC, null));
    }

}
