package com.example.demo.sevice.impls;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.NonExistingEntityException;
import com.example.demo.sevice.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(() ->
            new NonExistingEntityException(String.format("Employee with ID = %s does not exists.",id)));

    }

    @Override
    public List<Employee> getAllManagers() {
        return this.employeeRepository.getManagers();
    }

    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        employee.setId(null);
        if(employee.getManager() != null){
            employee.getManager().getSubordinates().add(employee);
        }
        return this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
       Employee existing = this.employeeRepository.getById(employee.getId());

       if(existing.getManager() != null && !existing.getManager().equals(employee.getManager())){
           existing.getManager().getSubordinates().remove(employee);
       }

       if(employee.getManager() != null && !employee.getManager().equals(existing.getManager())){
          employee.getManager().getSubordinates().add(employee);
       }

       return this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee deleteEmployee(Long id) {
        Employee removed = this.employeeRepository.getById(id);

        if(removed.getManager() != null){
            removed.getManager().getSubordinates().remove(removed);
        }

        this.employeeRepository.deleteById(id);
        return removed;
    }

    @Override
    public long getEmployeeCount() {
        return this.employeeRepository.count();
    }
}
