package com.example.xml.config;

import com.example.xml.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Configuration
public class COnfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
