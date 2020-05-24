package com.fbedir.invoiceRegistry.util;


import com.fbedir.invoiceRegistry.model.Bill;
import com.fbedir.invoiceRegistry.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillLimitControl {

    @Value("${accountant.bill.limit}")
    private Double billLimit;
    @Autowired
    private BillRepository billRepository;


    public Boolean checkBillLimit(Long accountantId, Double billAmount) {
        List<Bill> billList = billRepository.findOneByAccountantId(accountantId);
        if (billList != null && !billList.isEmpty()) {
            Double totalBillAmount = calculateTotalAmount(billList);
            return billLimit >= (totalBillAmount + billAmount);
        } else {
            return billLimit >= billAmount;
        }
    }

    private Double calculateTotalAmount(List<Bill> billList) {
        Double totalAmount = 0D;
        for (Bill bill : billList) {
            totalAmount += bill.getAmount();
        }
        return totalAmount;
    }
}
