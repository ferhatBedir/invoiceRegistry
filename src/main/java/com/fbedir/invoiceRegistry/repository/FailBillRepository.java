package com.fbedir.invoiceRegistry.repository;

import com.fbedir.invoiceRegistry.model.FailBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailBillRepository extends JpaRepository<FailBill,Long> {
}
