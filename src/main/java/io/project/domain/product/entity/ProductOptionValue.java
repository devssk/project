package io.project.domain.product.entity;

import io.project.domain.EntityTimestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class ProductOptionValue extends EntityTimestamp {

    public ProductOptionValue(long productOptionId, String productOptionString, int productOptionCost) {
        this.productOptionId = productOptionId;
        this.productOptionString = productOptionString;
        this.productOptionCost = productOptionCost;
        this.deleteYn = "N";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOptionValueId;

    private long productOptionId;

    private String productOptionString;

    private int productOptionCost;

    @Column(columnDefinition = "CHAR(1)")
    private String deleteYn;

    public void updateProductOptionValue(String productOptionString) {
        if (!this.productOptionString.equals(productOptionString)) {
            this.productOptionString = productOptionString;
        }
    }

    public void updateProductOptionCost(int productOptionCost) {
        if (this.productOptionCost != productOptionCost) {
            this.productOptionCost = productOptionCost;
        }
    }

    public void deleteProductOptionValue() {
        if (this.deleteYn.equals("N")) {
            this.deleteYn = "Y";
            onDelete();
        }
    }

}
