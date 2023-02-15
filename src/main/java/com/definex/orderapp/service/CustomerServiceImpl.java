package com.definex.orderapp.service;

import com.definex.orderapp.model.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Setter
@Getter
public class CustomerServiceImpl implements CustomerService {
    private Set<Customer> customerList = new HashSet<>();

    @Override
    public List<Customer> getCustomerList(String nameContains) {
        return customerList.stream().filter(customer -> customer.getName().contains(nameContains)).toList();
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerList.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }
}
