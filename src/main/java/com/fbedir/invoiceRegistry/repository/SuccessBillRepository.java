package com.fbedir.invoiceRegistry.repository;

import com.fbedir.invoiceRegistry.model.SuccessBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuccessBillRepository extends JpaRepository<SuccessBill, Long> {

    List<SuccessBill> findOneByAccountantId(Long accountantId);
}
