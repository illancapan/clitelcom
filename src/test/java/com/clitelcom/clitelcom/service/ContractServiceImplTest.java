package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.model.entity.Plan;
import com.clitelcom.clitelcom.repository.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContractServiceImplTest {

    ContractRepository contractRepository;
    ModelMapper modelMapper;
    ContractServiceImpl contractService;

    Contract activeContract, inactiveContract;
    ContractDTO activeContractDTO, inactiveContractDTO;
    ClientDTO clientDTO;
    PlanDTO planDTO;

    @BeforeEach
    void setUp() {

//        MOCK DEPENDENCIES
        contractRepository = Mockito.mock(ContractRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        contractService = new ContractServiceImpl(contractRepository, modelMapper);

        // MOCK CLIENT
        Client client = new Client();
        client.setId(1L);
        client.setName("Juan Doe");

        // MOCK PLAN
        Plan plan = new Plan();
        plan.setId(1L);
        plan.setName("Basic Plan");

        // MOCK CONTRACT ACTIVE
        activeContract = new Contract(
                1L,
                client,
                plan,
                LocalDate.now(),
                null,
                true);

        // MOCK CONTRACTDO ACTIVE
        activeContractDTO = ContractDTO.builder()
                .id(1L)
                .client(ClientDTO.builder()
                                .id(1L)
                                .name("Juan Doe")
                                .build())
                .plan(PlanDTO.builder()
                              .id(1L)
                              .name("Basic Plan")
                              .build())
                .startDate(LocalDate.now())
                .isActive(true)
                .build();
    }

    @Test
    void should_returnAllContracts_when_getAllContractsIsCalled() {

        when(contractRepository.findAll()).thenReturn(List.of(activeContract));
        when(modelMapper.map(activeContract, ContractDTO.class)).thenReturn(activeContractDTO);

        List<ContractDTO> contracts = contractService.getAllContracts();

        assertNotNull(contracts);
        assertFalse(contracts.isEmpty());
        assertEquals(1, contracts.size());
        assertNotNull(contracts.get(0));
        assertEquals(1L, contracts.get(0).getId());
    }

    @Test
    void should_returnEmptyList_when_noContractsExist() {
        when(contractRepository.findAll()).thenReturn(Collections.emptyList());

        List<ContractDTO> contracts = contractService.getAllContracts();

        assertNotNull(contracts);
        assertTrue(contracts.isEmpty());
    }

    @Test
    void should_createContract_when_createContractIsCalled() {
        ContractDTO contractDTO = ContractDTO.builder()
                .id(3L)
                .client(ClientDTO.builder()
                                .id(1L)
                                .name("Juan Doe")
                                .build())
                .plan(PlanDTO.builder()
                              .id(1L)
                              .name("Basic Plan")
                              .build())
                .startDate(LocalDate.now())
                .isActive(false)
                .build();

        Contract newContract = new Contract(3L, activeContract.getClient(), activeContract.getPlan(), LocalDate.now(), null, false);

        when(modelMapper.map(contractDTO, Contract.class)).thenReturn(newContract);
        when(contractRepository.save(newContract)).thenReturn(newContract);
        when(modelMapper.map(newContract, ContractDTO.class)).thenReturn(contractDTO);

        ContractDTO result = contractService.createContract(contractDTO);

        assertNotNull(result);
        assertFalse(result.isActive());
        assertEquals(3L, result.getId());
    }

    @Test
    void should_desactivateContract_when_deactiveContractIsCalled() {
        // Inicialización del contrato activo
        Contract activeContract = new Contract();
        activeContract.setId(1L);
        activeContract.setActive(true);
        activeContract.setEndDate(null);

        Long contractId = 1L;
        when(contractRepository.findById(contractId)).thenReturn(Optional.of(activeContract));

        contractService.deactiveContract(contractId);

        verify(contractRepository, times(1))
                .save(argThat(
                contract ->
                        !contract.isActive() && contract.getEndDate().equals(LocalDate.now())
        ));
    }


    @Test
    void should_throwException_when_deactiveContractIsCalledWithInvalidId() {

        Long contractId = 1L;
        when(contractRepository.findById(contractId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> contractService.deactiveContract(contractId));
        assertEquals("Contract not found", exception.getMessage());
    }

    @Test
    void should_return10PercentDiscount_when_calculateDiscountIsCalledWithTwoActiveContracts() {

        Long clientId = 1L;
        when(contractRepository.countByClientIdAndIsActiveTrue(clientId)).thenReturn(2L);

        Double discount = contractService.calculateDiscount(clientId);

        assertNotNull(discount);
        assertEquals(0.1, discount);
    }

    @Test
    void should_returnNoDiscount_when_calculateDiscountIsCalledWithLessThanTwoActiveContracts() {

        Long clientId = 1L;
        when(contractRepository.countByClientIdAndIsActiveTrue(clientId)).thenReturn(1L);

        Double discount = contractService.calculateDiscount(clientId);

        assertEquals(0.0, discount);
    }
}
