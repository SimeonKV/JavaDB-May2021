package com.workers.employees.configs;

import com.workers.employees.dto.EmployeeDTO;
import com.workers.employees.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        PropertyMap<Employee, EmployeeDTO> incomeEmployeeDto = new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setIncome(source.getSalary());
            }
        };

        modelMapper.addMappings(incomeEmployeeDto);

        return modelMapper;

    }



}
