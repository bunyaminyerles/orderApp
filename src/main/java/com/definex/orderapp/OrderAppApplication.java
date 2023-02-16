package com.definex.orderapp;

import com.definex.orderapp.model.*;
import com.definex.orderapp.service.*;
import com.definex.orderapp.util.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;


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
        customerService.addCustomer(Customer.builder().id(2).name("customer2").createdDate(dateUtil.getDateForMonth(Calendar.JUNE)).build());
        customerService.addCustomer(Customer.builder().id(3).name("customer3").createdDate(dateUtil.getDateForMonth(Calendar.AUGUST)).build());
        customerService.addCustomer(Customer.builder().id(4).name("customer4").createdDate(dateUtil.getDateForMonth(Calendar.FEBRUARY)).build());
        customerService.addCustomer(Customer.builder().id(5).name("customer5").createdDate(dateUtil.getDateForMonth(Calendar.DECEMBER)).build());
        customerService.addCustomer(Customer.builder().id(6).name("customer6").createdDate(dateUtil.getDateForMonth(Calendar.APRIL)).build());

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

        productService.addProduct(Product.builder().id(1).productName("A").unitPrice(50D).build());
        productService.addProduct(Product.builder().id(2).productName("B").unitPrice(1500D).build());
        productService.addProduct(Product.builder().id(3).productName("C").unitPrice(2000D).build());
        productService.addProduct(Product.builder().id(4).productName("D").unitPrice(4000D).build());
        productService.addProduct(Product.builder().id(5).productName("E").unitPrice(5000D).build());
        productService.addProduct(Product.builder().id(6).productName("F").unitPrice(1000D).build());
        productService.addProduct(Product.builder().id(7).productName("G").unitPrice(800D).build());
        productService.addProduct(Product.builder().id(8).productName("H").unitPrice(500D).build());
        productService.addProduct(Product.builder().id(9).productName("I").unitPrice(100D).build());


        orderService.addOrder(Order.builder()
                .id(1)
                .customerId(1)
                .sellerId(1)
                .createdDate(dateUtil.getDateForMonth(Calendar.JUNE))
                .productId(1).quantity(3).build());

        orderService.addOrder(Order.builder()
                .id(2)
                .customerId(2)
                .sellerId(2)
                .createdDate(dateUtil.getDateForMonth(Calendar.APRIL))
                .productId(2).quantity(5).build());

        orderService.addOrder(Order.builder()
                .id(3)
                .customerId(3)
                .sellerId(2)
                .createdDate(dateUtil.getDateForMonth(Calendar.MAY))
                .productId(3).quantity(2).build());

        orderService.addOrder(Order.builder()
                .id(4)
                .customerId(4)
                .sellerId(2)
                .createdDate(dateUtil.getDateForMonth(Calendar.AUGUST))
                .productId(4).quantity(5).build());

        orderService.addOrder(Order.builder()
                .id(5)
                .customerId(5)
                .sellerId(3)
                .createdDate(dateUtil.getDateForMonth(Calendar.NOVEMBER))
                .productId(5).quantity(1).build());

        orderService.addOrder(Order.builder()
                .id(6)
                .customerId(6)
                .sellerId(3)
                .createdDate(dateUtil.getDateForMonth(Calendar.DECEMBER))
                .productId(6).quantity(4).build());

        System.out.print("All Customer:");
        System.out.println(customerService.getCustomerList().stream().map(Customer::toString).toList());
        //new customer registration
        customerService.addCustomer(Customer.builder().id(7).name("customer7").createdDate(dateUtil.getDateForMonth(Calendar.JULY)).build());
        System.out.print("Customer that contains 'c' letter on the name field:");
        System.out.println(customerService.getCustomerList("c").stream().map(Customer::toString).toList());
        System.out.print("Total invoice amount for the customers with created date June: ");
        System.out.println(invoiceService.getInvoiceList(customerService.getCustomerByMonth(dateUtil.getDateForMonth(Calendar.JUNE))).stream().mapToDouble(Invoice::getInvoiceAmount).sum());
        System.out.print("All invoice :");
        System.out.println(invoiceService.getInvoiceList().stream().map(Invoice::toString).toList());
        System.out.print("Invoice amount above 1500 which invoices : ");
        System.out.println(invoiceService.getInvoiceList(1500D, true, false).stream().map(Invoice::toString).toList());
        System.out.print("The average amount for the invoices above 1500 : ");
        System.out.println(invoiceService.getInvoiceList(1500D, true, false).stream().mapToDouble(Invoice::getInvoiceAmount).average());
        System.out.print("Names of customers with invoice amount under 500 : ");
        System.out.println(customerService.getCustomerByListId(invoiceService.getInvoiceList(500D, false, false).stream().map(Invoice::getCustomerId).toList()));


        invoiceService.getSellerIdToInvoiceAmountList(
                invoiceService.getInvoiceListByMonth(dateUtil.getDateForMonth(Calendar.JUNE))).forEach((sellerId, invoiceAmountList) -> {
            invoiceAmountList.stream().mapToDouble(value -> value).average().ifPresent(value -> {
                if (value < 750D) {
                    sellerService.getSeller(sellerId).ifPresent(seller -> {
                        System.out.printf("The sector for the sellers whose invoices are created in June and average amount is below 750 is  %s, seller name is %s average amount is %f", seller.getSector(), seller.getSellerName(), value);
                    });
                }
            });
        });
    }
}
