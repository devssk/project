package io.project.domain.product.entity;

import io.project.domain.EntityTimestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product extends EntityTimestamp {

    public Product(String productName, String productDescription, long productPrice, int deliveryCost) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.deliveryCost = deliveryCost;
        this.deleteYn = "N";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String productName;

    @Column(columnDefinition = "TEXT")
    private String productDescription;

    private long productPrice;

    private int deliveryCost;

    @Column(columnDefinition = "CHAR(1)")
    private String deleteYn;

    public void updateProductName(String productName) {
        this.productName = productName;
    }

    public void updateProductDescription(String productDescription) {
        if (!this.productDescription.equals(productDescription)) {
            this.productDescription = productDescription;
        }
    }

    public void updateProductPrice(long productPrice) {
        if (this.productPrice != productPrice) {
            this.productPrice = productPrice;
        }
    }

    public void updateDeliveryCost(int deliveryCost) {
        if (this.deliveryCost != deliveryCost) {
            this.deliveryCost = deliveryCost;
        }
    }

    public void deleteProduct() {
        if (this.deleteYn.equals("N")) {
            this.deleteYn = "Y";
            onDelete();
        }
    }

}
