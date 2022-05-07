package com.example.demo.sevice;

import com.example.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

   List<Employee> getAllEmployees();
   Employee getEmployeeById(Long id);
   List<Employee> getAllManagers();
   Employee addEmployee(Employee employee);
   Employee updateEmployee(Employee employee);
   Employee deleteEmployee(Long id);
   long getEmployeeCount();

}
