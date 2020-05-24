package com.fbedir.invoiceRegistry.repository;

import com.fbedir.invoiceRegistry.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findOneByAccountantId(Long accountantId);

    List<Bill> findOneByBillState(Boolean state);
}
