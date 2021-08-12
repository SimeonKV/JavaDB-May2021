package com.workers.employees.services;

import com.workers.employees.dto.EmployeeCreateRequestDto;
import com.workers.employees.dto.EmployeeCreateResponseDto;
import com.workers.employees.dto.EmployeeDTO;
import com.workers.employees.dto.ManagerDto;
import com.workers.employees.entities.Employee;
import com.workers.employees.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public EmployeeDTO findEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow();

        EmployeeDTO employeeDTO =  modelMapper.map(employee,EmployeeDTO.class);

        return employeeDTO;
    }

    @Override
    public ManagerDto findManger(Long id) {
     Employee employee = this.employeeRepository.findById(id).orElseThrow();

        if (checkIfIsAManager(id, employee)) return null;

        ManagerDto managerDto = modelMapper.map(employee,ManagerDto.class);
     return managerDto;
    }

    @Override
    public List<ManagerDto> findAllManagers() {
        List<Employee> employees = this.employeeRepository.findAll();

        return employees.stream().filter(employee -> employee.getSubordinates().size() > 0)
                .map(employee -> modelMapper.map(employee,ManagerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeCreateResponseDto save(EmployeeCreateRequestDto createRequest) {
        Employee entity = this.modelMapper.map(createRequest,Employee.class);

        entity = this.employeeRepository.save(entity);

        EmployeeCreateResponseDto employeeCreateResponseDto =
                this.modelMapper.map(entity,EmployeeCreateResponseDto.class);

        return employeeCreateResponseDto;


    }


    private boolean checkIfIsAManager(Long id, Employee employee) {
        if(employee.getSubordinates().size() == 0){
            System.out.println("This employee with id: " + id + " is not a manager");
            return true;
        }
        return false;
    }
}
