package com.clitelcom.clitelcom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private List<ContractDTO> contractDTO;


}
