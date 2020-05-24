package com.fbedir.invoiceRegistry.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Bill implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "BILL_NO", nullable = false)
    private String billNo;

    @Column(name = "BILL_STATE", nullable = false)
    private Boolean billState;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNTANT_ID", referencedColumnName = "ID", nullable = false)
    private Accountant accountant;

}
