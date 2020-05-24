package com.fbedir.invoiceRegistry.service.impl;


import com.fbedir.invoiceRegistry.dto.AccountantDTO;
import com.fbedir.invoiceRegistry.dto.BillDTO;
import com.fbedir.invoiceRegistry.model.Accountant;
import com.fbedir.invoiceRegistry.model.Bill;
import com.fbedir.invoiceRegistry.model.Owner;
import com.fbedir.invoiceRegistry.repository.AccountantRepository;
import com.fbedir.invoiceRegistry.repository.OwnerRepository;
import com.fbedir.invoiceRegistry.service.BillService;
import com.fbedir.invoiceRegistry.util.BillLimitControl;
import com.fbedir.invoiceRegistry.util.VerificationProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private AccountantRepository accountantRepository;
    @Autowired
    private VerificationProcedure verificationProcedure;
    @Autowired
    private BillLimitControl billLimitControl;

    @Override
    public void addBill(BillDTO billDTO) {
        verificationProcedure.checkData(billDTO);
        boolean isBillSuccess = billLimitControl.checkBillLimit(billDTO.getAccountantId(), billDTO.getAmount());
        Owner owner = isExistOwner(billDTO.getFirstName(), billDTO.getLastName(), billDTO.getEmail());
        if (owner != null) {
            owner.getBillList().add(createNewBill(billDTO, isBillSuccess));
            ownerRepository.save(owner);
        } else {
            ownerRepository.save(createNewOwnerAndNewBill(billDTO, isBillSuccess));
        }
    }

    private Owner createNewOwnerAndNewBill(BillDTO billDTO, boolean isBillSuccess) {
        Owner owner = createNewOwner(billDTO);
        owner.getBillList().add(createNewBill(billDTO, isBillSuccess));
        return owner;
    }

    private Owner createNewOwner(BillDTO billDTO) {
        Owner newOwner = new Owner();
        newOwner.setFirstName(billDTO.getFirstName().toUpperCase());
        newOwner.setLastName(billDTO.getLastName().toUpperCase());
        newOwner.setEmail(billDTO.getEmail());
        newOwner.setBillList(new ArrayList<>());
        return newOwner;
    }

    private Bill createNewBill(BillDTO billDTO, boolean isBillSuccess) {
        Bill newBill = new Bill();
        newBill.setProductName(billDTO.getProductName());
        newBill.setAmount(billDTO.getAmount());
        newBill.setBillNo(billDTO.getBillNo());
        newBill.setBillState(isBillSuccess);
        newBill.setAccountant(findAccountantById(billDTO.getAccountantId()));
        return newBill;
    }

    private Owner isExistOwner(String firstName, String lastName, String email) {
        Owner owner = ownerRepository.findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
        if (owner != null) {
            owner = ownerRepository.findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndEmail(firstName, lastName, email);
            return owner;
        } else {
            return null;
        }
    }

    private Accountant findAccountantById(Long accountantId) {
        return accountantRepository.getOne(accountantId);
    }

    private AccountantDTO convertToAccountDTO(Accountant accountant) {
        AccountantDTO accountantDTO = new AccountantDTO();
        accountantDTO.setId(accountant.getId());
        accountantDTO.setName(accountant.getName());
        accountantDTO.setSurname(accountant.getSurname());
        return accountantDTO;
    }
}
