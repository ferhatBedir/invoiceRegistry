package com.fbedir.invoiceRegistry.service;

import com.fbedir.invoiceRegistry.dto.BillDTO;

import java.util.List;

public interface BillService {
    void addBill(BillDTO billDTO);

    List<BillDTO> getAllBill();

    List<BillDTO> getBillByState(Boolean state);

    List<BillDTO> getBillByOwnerInfo();
}
