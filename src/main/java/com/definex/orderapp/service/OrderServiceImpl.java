package com.definex.orderapp.service;

import com.definex.orderapp.model.Invoice;
import com.definex.orderapp.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class OrderServiceImpl implements OrderService {
    private Set<Order> orderList = new HashSet<>();

    public void addOrder(Order order) {
        orderList.add(order);
        InvoiceServiceImpl.addInvoice(
                Invoice
                        .builder()
                        .id(InvoiceServiceImpl.getLastInvoiceId()+1)
                        .sellerId(order.getSellerId())
                        .customerId(order.getCustomerId())
                        .invoiceAmount(order.getQuantity() * ProductServiceImpl.getProduct(order.getProductId()).get().getUnitPrice())
                        .build());
    }
}
