package com.chalanimantech.onlinegroceryshopping.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
public class Receipt extends BaseEntity {

    private BigDecimal fee;
    private LocalDateTime issuedOn;
    private User recipient;
    private Order order;

    public Receipt() {
    	super();
    }

    @Column(name = "fee", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Column(name = "issued_on", nullable = false)
    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @OneToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
