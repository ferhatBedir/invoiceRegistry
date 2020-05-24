package com.fbedir.invoiceRegistry.controller;


import com.fbedir.invoiceRegistry.dto.AccountantDTO;
import com.fbedir.invoiceRegistry.dto.ResponseDTO;
import com.fbedir.invoiceRegistry.service.AccountantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Accountant Controller API")
@RestController
@RequestMapping("/invoice/registry/accountant")
public class AccountantController {

    @Autowired
    private AccountantService accountantService;

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping("/add")
    public ResponseDTO persistAccountant(@RequestBody AccountantDTO accountantDTO) {
        return accountantService.addAccountant(accountantDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping("/find")
    public List<AccountantDTO> findAllAccountant() {
        return accountantService.getAllAccountant();
    }
}
