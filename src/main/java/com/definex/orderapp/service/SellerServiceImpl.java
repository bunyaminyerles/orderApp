package com.definex.orderapp.service;

import com.definex.orderapp.model.Seller;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Setter
@Getter
public class SellerServiceImpl implements SellerService {
    private Set<Seller> sellerList = new HashSet<>();

    public void addSeller(Seller seller) {
        sellerList.add(seller);
    }

    @Override
    public Optional<Seller> getSeller(Integer sellerId) {
        return sellerList.stream().filter(seller -> seller.getId().equals(sellerId)).findFirst();
    }

}
