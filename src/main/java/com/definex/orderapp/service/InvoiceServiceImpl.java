package com.definex.orderapp.service;

import com.definex.orderapp.model.Invoice;
import com.definex.orderapp.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class InvoiceServiceImpl implements InvoiceService {
    private static Set<Invoice> invoiceList = new HashSet<>();
    DateUtil dateUtil = new DateUtil();

    public static void addInvoice(Invoice invoice) {
        InvoiceServiceImpl.invoiceList.add(invoice);
    }

    public static Integer getLastInvoiceId() {
        final Iterator itr = InvoiceServiceImpl.invoiceList.iterator();
        Invoice invoice = null;
        while (itr.hasNext()) {
            invoice = (Invoice) itr.next();
        }
        return Optional.ofNullable(invoice).map(Invoice::getId).orElse(0);
    }

    public Set<Invoice> getInvoiceList() {
        return invoiceList;
    }

    @Override
    public List<Invoice> getInvoiceList(Double price, Boolean isAbove, Boolean isInclusive) {
        return invoiceList.stream().filter(invoice -> {
            if (isInclusive) {
                return isAbove ? invoice.getInvoiceAmount() >= price : invoice.getInvoiceAmount() <= price;
            } else {
                return isAbove ? invoice.getInvoiceAmount() > price : invoice.getInvoiceAmount() < price;
            }
        }).toList();
    }

    @Override
    public List<Invoice> getInvoiceList(List<Integer> customerIdList) {
        return invoiceList.stream().filter(invoice -> customerIdList.contains(invoice.getCustomerId())).toList();
    }

    public List<Invoice> getInvoiceListByMonth(Date date) {
        return invoiceList.stream().filter(invoice ->
                        Objects.equals(dateUtil.dateToCalendar(invoice.getInvoiceCreatedDate()).get(Calendar.YEAR), dateUtil.dateToCalendar(date).get(Calendar.YEAR)) &&
                                Objects.equals(dateUtil.dateToCalendar(invoice.getInvoiceCreatedDate()).get(Calendar.MONTH), dateUtil.dateToCalendar(date).get(Calendar.MONTH)))
                .collect(toList());
    }

    public Map<Integer, List<Double>> getSellerIdToInvoiceAmountList(List<Invoice> invoiceList) {
        Map<Integer, List<Double>> sellerIdToInvoiceAverageAmountMap = new HashMap<>();
        invoiceList.stream().forEach(invoice -> {
            if (sellerIdToInvoiceAverageAmountMap.containsKey(invoice.getSellerId())) {
                List<Double> list = sellerIdToInvoiceAverageAmountMap.get(invoice.getSellerId());
                list.add(invoice.getInvoiceAmount());
                sellerIdToInvoiceAverageAmountMap.put(invoice.getSellerId(), list);
            } else {
                sellerIdToInvoiceAverageAmountMap.put(invoice.getSellerId(), List.of(invoice.getInvoiceAmount()));
            }
        });
        return sellerIdToInvoiceAverageAmountMap;
    }

}
