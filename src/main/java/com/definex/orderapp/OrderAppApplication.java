package com.definex.orderapp;

import com.definex.orderapp.model.*;
import com.definex.orderapp.service.*;
import com.definex.orderapp.util.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class OrderAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderAppApplication.class, args);
        DateUtil dateUtil = new DateUtil();
        CustomerServiceImpl customerService = new CustomerServiceImpl();
        SellerServiceImpl sellerService = new SellerServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();
        InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();

        customerService.addCustomer(Customer.builder().id(1).name("customer1").createdDate(dateUtil.getDateForMonth(Calendar.JANUARY)).build());
        customerService.addCustomer(Customer.builder().id(2).name("customer2").createdDate(dateUtil.getDateForMonth(Calendar.JULY)).build());
        customerService.addCustomer(Customer.builder().id(3).name("customer3").createdDate(dateUtil.getDateForMonth(Calendar.AUGUST)).build());

        sellerService.addSeller(Seller.builder()
                .id(1)
                .sellerName("ZARA")
                .sector("CLOTHES")
                .build());
        sellerService.addSeller(Seller.builder()
                .id(2)
                .sellerName("BMW")
                .sector("CAR")
                .build());
        sellerService.addSeller(Seller.builder()
                .id(3)
                .sellerName("LOGITECH")
                .sector("TECHNOLOGY")
                .build());
        productService.addProduct(Product.builder().id(1).productName("A").unitPrice(50).build());
        productService.addProduct(Product.builder().id(2).productName("B").unitPrice(1500000).build());
        productService.addProduct(Product.builder().id(3).productName("C").unitPrice(5000).build());
        productService.addProduct(Product.builder().id(4).productName("D").unitPrice(5000).build());
        productService.addProduct(Product.builder().id(5).productName("E").unitPrice(5000).build());
        productService.addProduct(Product.builder().id(6).productName("F").unitPrice(5000).build());
        productService.addProduct(Product.builder().id(7).productName("G").unitPrice(5000).build());
        productService.addProduct(Product.builder().id(8).productName("H").unitPrice(5000).build());
        productService.addProduct(Product.builder().id(9).productName("I").unitPrice(5000).build());



        orderService.addOrder(Order.builder()
                                        .id(1)
                                        .customerId(1)
                                        .sellerId(1)
                                        .productId(1).quantity(3).build());

//        orderService.addOrder(Order.builder()
//                                        .customerId(1)
//                                        .sellerId(2)
//                                        .productSummaryList(
//                                                List.of(
//                                                        ProductSummary.builder().productId(3).quantity(2).build(),
//                                                        ProductSummary.builder().productId(8).quantity(5).build())
//                                        ).build());

//        orderService.addOrder(Order.builder().customerId(1).sellerId(2).build());
//        orderService.addOrder(Order.builder().customerId(2).sellerId(1).build());
//        orderService.addOrder(Order.builder().customerId(2).sellerId(2).build());


        System.out.println(invoiceService.getInvoiceList().stream().map(Invoice::toString).toList());

        System.out.println(customerService.getCustomerList().stream().map(Customer::toString).toList());
        System.out.println(sellerService.getSellerList().stream().map(Seller::toString).toList());
        System.out.println(invoiceService.getInvoiceList().stream().map(Invoice::toString).toList());



    }
}
