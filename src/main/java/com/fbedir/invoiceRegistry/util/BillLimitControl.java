package com.fbedir.invoiceRegistry.util;


import com.fbedir.invoiceRegistry.model.SuccessBill;
import com.fbedir.invoiceRegistry.repository.SuccessBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BillLimitControl {

    @Value("${accountant.successBill.limit}")
    private Double billLimit;
    @Autowired
    private SuccessBillRepository successBillRepository;


    public Boolean checkBillLimit(Long accountantId, Double billAmount) {
        List<SuccessBill> successBillList = successBillRepository.findOneByAccountantId(accountantId);
        if (successBillList != null && !successBillList.isEmpty()) {
            Double totalBillAmount = calculateTotalAmount(successBillList);
            return billLimit >= (totalBillAmount + billAmount);
        } else {
            return billLimit >= billAmount;
        }
    }

    private Double calculateTotalAmount(List<SuccessBill> successBillList) {
        Double totalAmount = 0D;
        for (SuccessBill successBill : successBillList) {
            totalAmount += successBill.getAmount();
        }
        return totalAmount;
    }
}
