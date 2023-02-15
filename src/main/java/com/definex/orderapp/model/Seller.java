package com.definex.orderapp.model;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Seller {
    private Integer id;
    private String sellerName;
    private String sector;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller seller)) return false;
        return getId().equals(seller.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", sellerName='" + sellerName + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }
}
