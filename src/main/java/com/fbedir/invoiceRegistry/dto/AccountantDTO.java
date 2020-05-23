package com.fbedir.invoiceRegistry.dto;


import lombok.Data;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Data
public class AccountantDTO {

    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Size(min = 3, max = 50)
    private String surname;
}
