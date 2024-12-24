package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ContractDTO;

import java.util.List;

public interface ContractService {
    List<ContractDTO> getAllContracts();
    ContractDTO createContract(ContractDTO contractDTO);
    void deactiveContract(Long id);
    Double calculateDiscount(Long clientId);
}
