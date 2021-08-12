package com.workers.employees.dto;

import com.google.gson.annotations.Expose;
import com.workers.employees.entities.Employee;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerDto extends BasicEmployeeDto{
    @Expose
    @XmlElementWrapper
    @XmlElement(name = "employee")
    List<EmployeeDTO> subordinates;

    public ManagerDto() {
    }

    public List<EmployeeDTO> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<EmployeeDTO> subordinates) {
        this.subordinates = subordinates;
    }
}
