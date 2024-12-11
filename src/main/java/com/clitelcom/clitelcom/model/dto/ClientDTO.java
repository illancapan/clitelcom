package com.clitelcom.clitelcom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    private String name;
    private String run;
    private String address;
    private LocalDate birthdate;

    private List<ContractDTO> contractDTOS;
}
