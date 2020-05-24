package com.fbedir.invoiceRegistry.service.impl;


import com.fbedir.invoiceRegistry.dto.AccountantDTO;
import com.fbedir.invoiceRegistry.dto.ResponseDTO;
import com.fbedir.invoiceRegistry.exception.BadRequestException;
import com.fbedir.invoiceRegistry.model.Accountant;
import com.fbedir.invoiceRegistry.repository.AccountantRepository;
import com.fbedir.invoiceRegistry.service.AccountantService;
import com.fbedir.invoiceRegistry.util.ResponseMessage;
import com.fbedir.invoiceRegistry.util.VerificationProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountantServiceImpl implements AccountantService {

    @Autowired
    private AccountantRepository accountantRepository;
    @Autowired
    private VerificationProcedure verificationProcedure;

    @Override
    public ResponseDTO addAccountant(AccountantDTO accountantDTO) {
        try {
            verificationProcedure.checkData(accountantDTO);
            accountantRepository.save(convertToAccountant(accountantDTO));
            return new ResponseDTO(ResponseMessage.SUCCESS);
        } catch (BadRequestException m) {
            return new ResponseDTO(ResponseMessage.FAIL);
        }
    }

    @Override
    public List<AccountantDTO> getAllAccountant() {
        return convertToAccountantDTOList(accountantRepository.findAll());
    }

    private List<AccountantDTO> convertToAccountantDTOList(List<Accountant> accountantList) {
        if (accountantList == null || accountantList.isEmpty()) {
            return null;
        }

        List<AccountantDTO> accountantDTOList = new ArrayList<>();
        for (Accountant accountant : accountantList) {
            accountantDTOList.add(convertToAccountantDTO(accountant));
        }
        return accountantDTOList;
    }

    private AccountantDTO convertToAccountantDTO(Accountant accountant) {
        AccountantDTO accountantDTO = new AccountantDTO();
        accountantDTO.setId(accountant.getId());
        accountantDTO.setName(accountant.getName());
        accountantDTO.setSurname(accountant.getSurname());
        return accountantDTO;
    }

    private Accountant convertToAccountant(AccountantDTO accountantDTO) {
        Accountant accountant = new Accountant();
        accountant.setName(accountantDTO.getName());
        accountant.setSurname(accountantDTO.getSurname());
        return accountant;
    }
}
