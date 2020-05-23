package com.fbedir.invoiceRegistry.service.impl;


import com.fbedir.invoiceRegistry.dto.AccountantDTO;
import com.fbedir.invoiceRegistry.dto.BillDTO;
import com.fbedir.invoiceRegistry.model.Accountant;
import com.fbedir.invoiceRegistry.model.FailBill;
import com.fbedir.invoiceRegistry.model.RootBill;
import com.fbedir.invoiceRegistry.model.SuccessBill;
import com.fbedir.invoiceRegistry.repository.AccountantRepository;
import com.fbedir.invoiceRegistry.repository.FailBillRepository;
import com.fbedir.invoiceRegistry.repository.SuccessBillRepository;
import com.fbedir.invoiceRegistry.service.BillService;
import com.fbedir.invoiceRegistry.util.BillLimitControl;
import com.fbedir.invoiceRegistry.util.VerificationProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BillServiceImpl implements BillService {

    @Autowired
    private SuccessBillRepository successBillRepository;
    @Autowired
    private FailBillRepository failBillRepository;
    @Autowired
    private AccountantRepository accountantRepository;
    @Autowired
    private VerificationProcedure verificationProcedure;
    @Autowired
    private BillLimitControl billLimitControl;

    @Override
    public void addBill(BillDTO billDTO) {
        verificationProcedure.checkData(billDTO);
        Boolean isBillSuccess = billLimitControl.checkBillLimit(billDTO.getAccountantId(), billDTO.getAmount());
        if (isBillSuccess) {
            successBillRepository.save(convertToRootBill(billDTO, new SuccessBill()));
        } else {
            failBillRepository.save((convertToRootBill(billDTO, new FailBill())));
        }
    }

    @Override
    public List<BillDTO> getActiveBill() {
        return convertToBillDTOList(successBillRepository.findAll());
    }

    @Override
    public List<BillDTO> getFailBill() {
        return convertToBillDTOList(failBillRepository.findAll());
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

    private <T extends RootBill> List<BillDTO> convertToBillDTOList(List<T> rootBillList) {
        if (rootBillList == null || rootBillList.isEmpty()) {
            return null;
        }

        List<BillDTO> billDTOList = new ArrayList<>();
        for (RootBill rootBill : rootBillList) {
            billDTOList.add(convertToBillDTO(rootBill));
        }
        return billDTOList;
    }

    private <T extends RootBill> BillDTO convertToBillDTO(T rootBill) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(rootBill.getId());
        billDTO.setFirstName(rootBill.getFirstName());
        billDTO.setLastName(rootBill.getLastName());
        billDTO.setEmail(rootBill.getEmail());
        billDTO.setProductName(rootBill.getProductName());
        billDTO.setAmount(rootBill.getAmount());
        billDTO.setBillNo(rootBill.getBillNo());
        billDTO.setAccountantDTO(convertToAccountDTO(rootBill.getAccountant()));
        return billDTO;
    }

    private <T extends RootBill> T convertToRootBill(BillDTO billDTO, T rootBill) {
        rootBill.setFirstName(billDTO.getFirstName());
        rootBill.setLastName(billDTO.getLastName());
        rootBill.setEmail(billDTO.getEmail());
        rootBill.setProductName(billDTO.getProductName());
        rootBill.setAmount(billDTO.getAmount());
        rootBill.setBillNo(billDTO.getBillNo());
        rootBill.setAccountant(findAccountantById(billDTO.getAccountantId()));
        return rootBill;
    }
}
