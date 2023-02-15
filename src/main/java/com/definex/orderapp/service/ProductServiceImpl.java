package com.definex.orderapp.service;

import com.definex.orderapp.model.Invoice;
import com.definex.orderapp.model.Order;
import com.definex.orderapp.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class ProductServiceImpl implements ProductService {
    private static Set<Product> productList = new HashSet<>();

    public void addProduct(Product product) {
        ProductServiceImpl.productList.add(product);
    }

    public static Optional<Product> getProduct(Integer id) {
        return ProductServiceImpl.productList.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

}
