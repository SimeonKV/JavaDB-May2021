package com.example.demo.dto;

import com.example.demo.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ManagerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<EmployeeDTO> employees;

    private int getSubordinatesNumber(){
        return this.employees.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": ").append(firstName);
        sb.append(" ").append(lastName);
        sb.append(", Employees: ").append(getSubordinatesNumber()).append("\n");
        sb.append(employees.stream().map(emp -> emp.toString()).collect(Collectors.joining("\n")));
        return sb.toString();
    }
}
