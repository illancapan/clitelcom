package com.clitelcom.clitelcom.service;


import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.repository.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

class ContractServiceImplTest {

    ContractRepository contractRepository;
    ModelMapper modelMapper;
    ContractServiceImpl contractService;

    Contract activeContractClient, inactiveContractClient;
    ContractDTO activeContractDTOClient, inactiveContractDTOClient;


    @BeforeEach
    void setUp() {
        activeContractClient = new Contract(
                1L,
                "Juan Doe",
                null,
                LocalDate.now(),
                LocalDate.now(),
                true);  // Contrato activo para el cliente 1
        inactiveContractClient = new Contract(
                1L,
                "Juan Doe",
                null,
                LocalDate.now(),
                LocalDate.now(),
                true);
        ;  // Contrato inactivo para el cliente 1

        activeContractDTOClient = new ContractDTO (
                1L,
                "Juan Doe",
                null,
                LocalDate.now(),
                LocalDate.now(),
                true);
        inactiveContractDTOClient = new ContractDTO(
                1L,
                "Juan Doe",
                null,
                LocalDate.now(),
                LocalDate.now(),
                true);  // DTO de contrato inactivo
    }
}