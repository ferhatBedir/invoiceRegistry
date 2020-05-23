package com.fbedir.invoiceRegistry.model;

import lombok.Data;

import javax.persistence.*;


@Data
@MappedSuperclass
public class RootBill {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "BILL_NO", nullable = false)
    private String billNo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNTANT", referencedColumnName = "ID", nullable = false)
    private Accountant accountant;

    @Override
    public String toString() {
        return "RootBill{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", amount=" + amount +
                ", productName='" + productName + '\'' +
                ", billNo='" + billNo + '\'' +
                ", accountant=" + accountant +
                '}';
    }
}
