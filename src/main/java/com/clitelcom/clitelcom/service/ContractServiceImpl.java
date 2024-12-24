package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        contractDTO.setActive(false);

        Contract contract = modelMapper.map(contractDTO, Contract.class);
        contract = contractRepository.save(contract);

        return modelMapper.map(contract, ContractDTO.class);
    }

    @Override
    @Transactional
    public void deactiveContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        contract.setActive(false);
        contract.setEndDate(LocalDate.now());

        contractRepository.save(contract);
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
