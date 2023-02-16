package com.definex.orderapp.model;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Order {
    private Integer id;
    private Integer customerId;
    private Integer sellerId;
    private Integer productId;
    private Integer quantity;
    private Date createdDate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", sellerId=" + sellerId +
                '}';
    }
}