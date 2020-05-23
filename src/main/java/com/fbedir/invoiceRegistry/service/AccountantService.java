package com.fbedir.invoiceRegistry.service;


import com.fbedir.invoiceRegistry.dto.AccountantDTO;

import java.util.List;

public interface AccountantService {

    void addAccountant(AccountantDTO accountantDTO);

    List<AccountantDTO> getAllAccountant();
}
