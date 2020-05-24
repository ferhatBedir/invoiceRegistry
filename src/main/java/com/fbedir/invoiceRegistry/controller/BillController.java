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

    @GetMapping("/find/all")
    public List<BillDTO> findAllBill() {
        return billService.getAll();
    }

    @GetMapping("/find/state")
    public List<BillDTO> findAllBillByBillState(@RequestParam(value = "value") Boolean state) {
        return billService.getBillByState(state);
    }

    @GetMapping("/find/info")
    public List<BillDTO> findAllBillByOwnerInfo(@RequestParam(value = "name") String name,
                                                @RequestParam(value = "surname") String surname,
                                                @RequestParam(value = "email") String email) {
        return billService.getBillByOwnerInfo(name, surname, email);
    }


}
