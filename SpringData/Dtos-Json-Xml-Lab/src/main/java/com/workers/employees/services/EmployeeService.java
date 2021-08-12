package com.workers.employees.services;

import com.workers.employees.dto.EmployeeCreateRequestDto;
import com.workers.employees.dto.EmployeeCreateResponseDto;
import com.workers.employees.dto.EmployeeDTO;
import com.workers.employees.dto.ManagerDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO findEmployee(Long id);
    ManagerDto findManger(Long id);
    List<ManagerDto> findAllManagers();
    EmployeeCreateResponseDto save(EmployeeCreateRequestDto createRequest);

}
