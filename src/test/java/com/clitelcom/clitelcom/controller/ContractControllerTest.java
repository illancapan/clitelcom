package com.clitelcom.clitelcom.controller;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.dto.ContractDTO;
import com.clitelcom.clitelcom.dto.PlanDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import com.clitelcom.clitelcom.model.entity.Contract;
import com.clitelcom.clitelcom.model.entity.Plan;
import com.clitelcom.clitelcom.repository.ContractRepository;
import com.clitelcom.clitelcom.service.ContractServiceImpl;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ContractControllerTest {


    ContractDTO contractDTO;
    ClientDTO clientDto;
    List<ContractDTO> expectedBody;
    ContractServiceImpl contractService;
    ContractController contractController;
    ModelMapper modelMapper;
    ContractRepository contractRepository;

    PlanDTO planDTO;

    @BeforeEach
    void setUp() {

        clientDto = new ClientDTO(
                1L,
                "John Doe",
                "12345678",
                "Siempre viva 231",
                LocalDate.now(),
                null);
        planDTO = new PlanDTO(
                1L,
                "Basic Plan",
                29.0,
                true,
                null);
        contractDTO = new ContractDTO(
                1L,
                clientDto,
                planDTO,
                null,
                null,
                true
        );
        expectedBody = Arrays.asList(contractDTO);
        contractService = Mockito.mock(ContractServiceImpl.class);
        contractController = new ContractController(contractService);


    }

    @Test
    void getAllContracts_ShouldReturnListOfContracts_Return200() {
        when(contractService.getAllContracts()).thenReturn(expectedBody);

        ResponseEntity<List<ContractDTO>> actual = contractController.getAllContracts();

        Assertions.assertEquals(200, actual.getStatusCode().value());
        Assertions.assertEquals(expectedBody, actual.getBody());
    }

//    @Test
//    void createContract_ShouldCreateAndReturnContractDTO() {
////        Contract contract = new Contract();
////        contract.setId(1L);
////        contract.setClient(new Client());
////        contract.setPlan(new Plan());
////        contract.setIsActive(true);
//
//        when(modelMapper.map(contractDTO, Contract.class)).thenReturn(contractDTO);
//        when(contractRepository.save(contract)).thenReturn(contract);
//        when(modelMapper.map(contract, ContractDTO.class)).thenReturn(contractDTO);
//
//        ContractDTO result = contractService.createContract(contractDTO);
//
//        assertNotNull(result);
//        Assertions.assertEquals(contractDTO.getId(), result.getId());
//        Assertions.assertEquals(contractDTO.getClient(), result.getClient());
//        Assertions.assertEquals(contractDTO.getPlan(), result.getPlan());
//        Assertions.assertEquals(contractDTO.getStartDate(), result.getStartDate());
//        Assertions.assertEquals(contractDTO.getEndDate(), result.getEndDate());
//        Assertions.assertEquals(contractDTO.getIsActive(), result.getIsActive());
//    }
}
