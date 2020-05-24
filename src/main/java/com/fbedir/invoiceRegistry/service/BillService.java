package com.fbedir.invoiceRegistry.service;

import com.fbedir.invoiceRegistry.dto.BillDTO;
import com.fbedir.invoiceRegistry.dto.ResponseDTO;

import java.util.List;

public interface BillService {
    ResponseDTO addBill(BillDTO billDTO);

    List<BillDTO> getAll();

    List<BillDTO> getBillByState(Boolean state);

    List<BillDTO> getBillByOwnerInfo(String firstName, String lastName, String email);
}
