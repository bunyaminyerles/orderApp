package com.definex.orderapp.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
public class Product {
    private Integer id;
    private String productName;
    private Integer unitPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getId().equals(product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
