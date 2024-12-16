package com.clitelcom.clitelcom.config;

import com.clitelcom.clitelcom.dto.ClientDTO;
import com.clitelcom.clitelcom.model.entity.Client;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // ClientDTO a Client
        modelMapper.addMappings(new PropertyMap<ClientDTO, Client>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                map(source.getName(), destination.getName());
                map(source.getRun(), destination.getRun());
                map(source.getAddress(), destination.getAddress());
                map(source.getBirthDate(), destination.getBirthDate());
            }
        });

        // Client a ClientDTO
        modelMapper.addMappings(new PropertyMap<Client, ClientDTO>() {
            @Override
            protected void configure() {
                // Mapea los campos de Client a ClientDTO
                map(source.getName(), destination.getName());
                map(source.getRun(), destination.getRun());
                map(source.getAddress(), destination.getAddress());
                map(source.getBirthDate(), destination.getBirthDate());
            }
        });

        return modelMapper;
    }
}
