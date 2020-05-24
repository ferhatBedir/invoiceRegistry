package com.fbedir.invoiceRegistry.controller;


import com.fbedir.invoiceRegistry.dto.BillDTO;
import com.fbedir.invoiceRegistry.dto.ResponseDTO;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.POST)
    @PostMapping("/add")
    public ResponseDTO persistBill(@RequestBody BillDTO billDTO) {
        return billService.addBill(billDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping("/find/all")
    public List<BillDTO> findAllBill() {
        return billService.getAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping("/find/state")
    public List<BillDTO> findAllBillByBillState(@RequestParam(value = "value") Boolean state) {
        return billService.getBillByState(state);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET)
    @GetMapping("/find/info")
    public List<BillDTO> findAllBillByOwnerInfo(@RequestParam(value = "name") String name,
                                                @RequestParam(value = "surname") String surname,
                                                @RequestParam(value = "email") String email) {
        return billService.getBillByOwnerInfo(name, surname, email);
    }


}
