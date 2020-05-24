package com.fbedir.invoiceRegistry.controller;


import com.fbedir.invoiceRegistry.dto.BillDTO;
import com.fbedir.invoiceRegistry.service.BillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Bill Controller API")
@RestController
@RequestMapping("/invoice/registry/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/add")
    public void persistBill(@RequestBody BillDTO billDTO) {
        billService.addBill(billDTO);
    }

    @GetMapping("/find/success")
    public List<BillDTO> findActiveBill() {
        //return billService.getActiveBill();
        return null;
    }

    @GetMapping("/find/fail")
    public List<BillDTO> findFailBill() {
        //return billService.getFailBill();
        return null;
    }
}
