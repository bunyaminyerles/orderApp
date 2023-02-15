package com.definex.orderapp.service;

import com.definex.orderapp.model.Seller;

import java.util.Optional;

public interface SellerService {
    Optional<Seller> getSeller(Integer sellerId);
}
