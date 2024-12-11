package com.clitelcom.clitelcom.model.dto;

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
     private PlanDTO planDTO;
     private LocalDate activationDate;
     private Boolean active;
}
