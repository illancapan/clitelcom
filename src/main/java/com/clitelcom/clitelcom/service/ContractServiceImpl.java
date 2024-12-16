package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.repository.ContractRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(contract -> modelMapper.map(contract, ContractDTO.class))
//                .map(contract -> new ContractDTO(
//                        contract.getId(),
//                        contract.getClient(),
//                        contract.getPlan(),
//                        contract.getStartDate(),
//                        contract.getEndDate(),
//                        contract.getIsActive()))
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
        var contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado"));

        contract.setIsActive(false);
        contract.setEndDate(LocalDate.now());
        contract = contractRepository.save(contract);

        return contract;
    }

    @Override
    public Double calculateDiscount(Long clientId) {
        long activeContracts = contractRepository.countByClientIdAndIsActiveTrue(clientId);

        if (activeContracts >= 2) {
            return 0.1; // descuento del 10% del segundo contrato
        }
        return 0.0; // sin descuento
    }

}
