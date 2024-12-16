package com.clitelcom.clitelcom.repository;

import com.clitelcom.clitelcom.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    long countByClientIdAndIsActiveTrue(Long clientId);
}
