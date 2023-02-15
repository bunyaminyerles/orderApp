package com.definex.model;

import java.util.List;

public class Order {
    private Integer id;
    private Customer customer;
    private List<Product> product;
    private Integer quantity;
    private Double totalPrice;
}
