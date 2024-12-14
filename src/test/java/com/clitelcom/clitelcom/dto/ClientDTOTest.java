package com.clitelcom.clitelcom.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientDTOTest {

    @Test
    public void testSerializeLocalDate() throws JsonProcessingException {
        // Arrange
        ClientDTO clientDTO = new ClientDTO();
        LocalDate today = LocalDate.now();  // Establece la fecha actual
        clientDTO.setBirthDate(today);

        // Act
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // Registra explícitamente el módulo para Java 8 Date/Time
        String json = objectMapper.writeValueAsString(clientDTO);

        // Assert
        System.out.println(json);  // Imprime el JSON generado en consola
        assertTrue(json.contains("birthDate"));  // Verifica que 'birthDate' esté presente en el JSON
        assertTrue(json.contains(today.toString()));  // Verifica que la fecha esté correcta en formato String
    }
}
