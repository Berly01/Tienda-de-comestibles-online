package com.chalanimantech.onlinegroceryshopping.domain.entities;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "order_products")
public class OrderProduct extends BaseEntity {

    private Product product;
    private BigDecimal price;

    public OrderProduct() {
    	super();
    }

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
