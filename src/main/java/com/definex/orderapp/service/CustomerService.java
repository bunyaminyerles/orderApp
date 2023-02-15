package com.definex.orderapp.service;

import com.definex.orderapp.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomerList(String nameContains);
}
