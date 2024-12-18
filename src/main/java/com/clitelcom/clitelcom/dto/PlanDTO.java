package com.clitelcom.clitelcom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

    private Long id;
    private String name;
    private Double price;
    private boolean isActive;

    private List<ContractDTO> contract;

}
