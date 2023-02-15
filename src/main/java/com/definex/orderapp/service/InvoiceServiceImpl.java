package com.definex.orderapp.service;

import com.definex.orderapp.model.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class InvoiceServiceImpl implements InvoiceService {
    private static Set<Invoice> invoiceList = new HashSet<>();

    public Set<Invoice> getInvoiceList() {
        return invoiceList;
    }

    @Override
    public List<Invoice> getInvoiceList(Double price, Boolean isAbove, Boolean isInclusive) {
        return invoiceList.stream().filter(invoice -> {
            if(isInclusive) {
                return isAbove ? invoice.getInvoiceAmount() >= price : invoice.getInvoiceAmount() <= price;
            } else {
                return isAbove ? invoice.getInvoiceAmount() > price : invoice.getInvoiceAmount() < price;
            }
        }).toList();
    }

    @Override
    public List<Invoice> getInvoiceList(Date date) {
        return invoiceList.stream().filter(invoice -> invoice.getInvoiceCreatedDate().after(date)).toList();
    }

    @Override
    public List<Invoice> getInvoiceList(List<Integer> customerIdList) {
        return invoiceList.stream().filter(invoice -> customerIdList.contains(invoice.getCustomerId())).toList();
    }

    public static void addInvoice(Invoice invoice) {
        InvoiceServiceImpl.invoiceList.add(invoice);
    }
    public static Integer getLastInvoiceId() {
        final Iterator itr = InvoiceServiceImpl.invoiceList.iterator();
        Object lastElement = itr.next();
        while(itr.hasNext()) {
            lastElement = itr.next();
        }
        return ((Invoice)lastElement).getId();
    }
}
