package com.clitelcom.clitelcom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {

     private Long id;
     private ClientDTO client;
     private PlanDTO plan;
     private LocalDate startDate;
     private LocalDate endDate;
     private Boolean isActive;
}
