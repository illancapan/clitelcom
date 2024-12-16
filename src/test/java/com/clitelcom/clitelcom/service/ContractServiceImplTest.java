package com.clitelcom.clitelcom.service;

import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.repository.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContractServiceImplTest {

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ContractServiceImpl contractService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllContracts_ShouldReturnListOfContractDTO() {
        Contract contract1 = new Contract(
                1L,
                null,
                null,
                LocalDate.now(),
                LocalDate.now().plusMonths(12),
                true);
        Contract contract2 = new Contract(
                2L,
                null,
                null,
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                true);

        when(contractRepository.findAll()).thenReturn(Arrays.asList(contract1, contract2));

        when(modelMapper.map(contract1, ContractDTO.class)).thenReturn(new ContractDTO(
                1L,
                null,
                null,
                LocalDate.now(),
                LocalDate.now().plusMonths(12),
                true));
        when(modelMapper.map(contract2, ContractDTO.class)).thenReturn(new ContractDTO(
                2L,
                null,
                null,
                LocalDate.now(),
                LocalDate.now().plusMonths(6),
                true));

        List<ContractDTO> result = contractService.getAllContracts();

        assertNotNull(result);
        assertEquals(2, result.size());
    }


    @Test
    void createContract_ShouldCreateAndReturnContractDTO() {
        ContractDTO contractDTO = new ContractDTO(
                null,
                null,
                null,
                LocalDate.now(),
                LocalDate.now().plusMonths(12),
                true);

        Contract contract = new Contract(
                null,
                null,
                null,
                LocalDate.now(),
                LocalDate.now().plusMonths(12),
                true);

        when(modelMapper.map(contractDTO, Contract.class)).thenReturn(contract);
        when(contractRepository.save(contract)).thenReturn(contract);
        when(modelMapper.map(contract, ContractDTO.class)).thenReturn(contractDTO);

        ContractDTO result = contractService.createContract(contractDTO);

        assertNotNull(result);
    }



    @Test
    void desactivateContract_ShouldReturnDeactivatedContractDTO() {
        Contract contract = new Contract(
                1L,
                null,
                null,
                LocalDate.now(),
                LocalDate.now().plusMonths(12),
                true);
        when(contractRepository.findById(1L)).thenReturn(Optional.of(contract));
        when(contractRepository.save(contract)).thenReturn(contract);

        Contract result = contractService.desactiveContract(1L);

        assertNotNull(result);
        assertFalse(result.getIsActive());
        assertEquals(LocalDate.now(), result.getEndDate());
        verify(contractRepository, times(1)).findById(1L);
        verify(contractRepository, times(1)).save(contract);
    }


    @Test
    void calculateDiscount_ShouldReturnDiscount_WhenClientHasMultipleActiveContracts() {
        Long clientId = 1L;
        when(contractRepository.countByClientIdAndIsActiveTrue(clientId)).thenReturn(2L);

        Double result = contractService.calculateDiscount(clientId);

        assertEquals(0.1, result);  //descuento sea del 10%
        verify(contractRepository, times(1)).countByClientIdAndIsActiveTrue(clientId);  //  el repositorio fue invocado una vez
    }

    @Test
    void calculateDiscount_ShouldReturnNoDiscount_WhenClientHasLessThanTwoActiveContracts() {
        Long clientId = 1L;
        when(contractRepository.countByClientIdAndIsActiveTrue(clientId)).thenReturn(1L);

        Double result = contractService.calculateDiscount(clientId);

        assertEquals(0.0, result);  // no haya descuento
        verify(contractRepository, times(1)).countByClientIdAndIsActiveTrue(clientId); //repositorio fue invocado una vez
    }

}