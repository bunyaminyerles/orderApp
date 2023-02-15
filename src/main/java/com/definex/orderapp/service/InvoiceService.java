package com.definex.orderapp.service;

import com.definex.orderapp.model.Invoice;

import java.util.Date;
import java.util.List;

public interface InvoiceService {
    List<Invoice> getInvoiceList(Double price, Boolean isAbove, Boolean isInclusive);
    List<Invoice> getInvoiceList(Date date);
    List<Invoice> getInvoiceList(List<Integer> customerIdList);
}
