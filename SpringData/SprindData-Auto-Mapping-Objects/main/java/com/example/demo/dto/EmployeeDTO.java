package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private double salary;
    private String addressCity;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("  - ").append(id);
        sb.append(": ").append(firstName);
        sb.append(" ").append(lastName);
        sb.append(", salary: ").append(salary);


        return sb.toString();
    }

}
