package com.fbedir.invoiceRegistry.repository;

import com.fbedir.invoiceRegistry.model.Accountant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountantRepository extends JpaRepository<Accountant, Long> {
}
