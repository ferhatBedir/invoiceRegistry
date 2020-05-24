package com.fbedir.invoiceRegistry.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BillDTO {

    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 50)
    private String lastName;
    @NotNull
    @Size(min = 3, max = 50)
    private String email;
    @NotNull
    private Double amount;
    @NotNull
    @Size(min = 3, max = 128)
    private String productName;
    @NotNull
    @Size(min = 3, max = 128)
    private String billNo;
    @NotNull
    private Long accountantId;
    private AccountantDTO accountantDTO;
    private Boolean billState;
}
