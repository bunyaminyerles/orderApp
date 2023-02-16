package com.definex.orderapp.service;

import com.definex.orderapp.model.Customer;
import com.definex.orderapp.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;


@Setter
@Getter
public class CustomerServiceImpl implements CustomerService {
    DateUtil dateUtil = new DateUtil();
    private Set<Customer> customerList = new HashSet<>();

    @Override
    public List<Customer> getCustomerList(String nameContains) {
        return customerList.stream().filter(customer -> customer.getName().contains(nameContains)).toList();
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Integer> getCustomerByMonth(Date date) {
        return customerList.stream().filter(customer ->
                        Objects.equals(dateUtil.dateToCalendar(customer.getCreatedDate()).get(Calendar.YEAR), dateUtil.dateToCalendar(date).get(Calendar.YEAR)) &&
                                Objects.equals(dateUtil.dateToCalendar(customer.getCreatedDate()).get(Calendar.MONTH), dateUtil.dateToCalendar(date).get(Calendar.MONTH)))
                .map(Customer::getId).collect(Collectors.toList());
    }

    public List<String> getCustomerByListId(List<Integer> customerIdList) {
        return customerList.stream().filter(customer -> customerIdList.contains(customer.getId())).map(Customer::getName).toList();
    }
}
