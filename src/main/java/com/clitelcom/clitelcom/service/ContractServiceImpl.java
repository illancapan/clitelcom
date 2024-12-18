package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(contract -> modelMapper.map(contract, ContractDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContractDTO createContract(ContractDTO contractDTO) {
        contractDTO.setStartDate(LocalDate.now());
        contractDTO.setIsActive(true);

        Contract contract = modelMapper.map(contractDTO, Contract.class);
        contract = contractRepository.save(contract);

        return modelMapper.map(contract, ContractDTO.class);
    }

    @Override
    public Contract desactiveContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        contract.setIsActive(false);
        contract.setEndDate(LocalDate.now());
        contract = contractRepository.save(contract);

        return contract;
    }

    @Override
    public Double calculateDiscount(Long clientId) {
        long activeContracts = contractRepository.countByClientIdAndIsActiveTrue(clientId);

        if (activeContracts >= 2) {
            return 0.1; // discount del 10% segundo contract
        }
        return 0.0; // sin discount
    }

}
