package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.model.entity.Contract;

import java.util.List;

public interface ContractService {
    List<ContractDTO> getAllContracts();
    ContractDTO createContract(ContractDTO contractDTO);
    Contract desactiveContract(Long id);
    Double calculateDiscount(Long clientId);
}
