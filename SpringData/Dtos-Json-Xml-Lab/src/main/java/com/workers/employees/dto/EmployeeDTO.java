package com.workers.employees.dto;

import java.math.BigDecimal;

public class EmployeeDTO extends BasicEmployeeDto{

    private BigDecimal income;

    public EmployeeDTO() {
    }



    public BigDecimal getSalary() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
