package com.clitelcom.clitelcom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

    private Long id;
    private String name;
    private Double price;
    private List<String> serviceFacilitated;
    private List<ContractDTO> contractDTOS;
}
