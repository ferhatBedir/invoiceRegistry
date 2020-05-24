package com.fbedir.invoiceRegistry.repository;

import com.fbedir.invoiceRegistry.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

    Owner findFirstByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndEmail(String firstName, String lastName, String email);
}
