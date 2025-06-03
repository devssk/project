package io.project.domain.product.entity;

import io.project.domain.EntityTimestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        indexes = {
                @Index(name = "idx_product_option", columnList = "productId")
        }
)
public class ProductOption extends EntityTimestamp {

    public ProductOption(long productId, String productOptionName, ProductOptionType productOptionType) {
        this.productId = productId;
        this.productOptionName = productOptionName;
        this.productOptionType = productOptionType;
        this.deleteYn = "N";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOptionId;

    private long productId;

    private String productOptionName;

    @Enumerated(EnumType.STRING)
    private ProductOptionType productOptionType;

    @Column(columnDefinition = "CHAR(1)")
    private String deleteYn;

    public void updateProductOptionName(String productOptionName) {
        if (!this.productOptionName.equals(productOptionName)) {
            this.productOptionName = productOptionName;
        }
    }

    public void updateProductOptionType(ProductOptionType productOptionType) {
        if (!this.productOptionType.equals(productOptionType)) {
            this.productOptionType = productOptionType;
        }
    }

    public void deleteProductOption() {
        if (this.deleteYn.equals("N")) {
            this.deleteYn = "Y";
            onDelete();
        }
    }

}
