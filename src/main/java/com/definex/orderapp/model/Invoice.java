package com.definex.orderapp.model;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Invoice {
    private Integer id;
    private Integer orderId;
    private Integer customerId;
    private Integer sellerId;
    private Double invoiceAmount;
    private Date invoiceCreatedDate;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice invoice)) return false;
        return getId().equals(invoice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", customerId=" + customerId +
                ", sellerId=" + sellerId +
                ", invoiceAmount=" + invoiceAmount +
                ", invoiceCreatedDate=" + invoiceCreatedDate +
                '}';
    }
}
