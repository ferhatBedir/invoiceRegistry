package com.fbedir.invoiceRegistry.service;


import com.fbedir.invoiceRegistry.dto.AccountantDTO;
import com.fbedir.invoiceRegistry.dto.ResponseDTO;

import java.util.List;

public interface AccountantService {

    ResponseDTO addAccountant(AccountantDTO accountantDTO);

    List<AccountantDTO> getAllAccountant();
}
