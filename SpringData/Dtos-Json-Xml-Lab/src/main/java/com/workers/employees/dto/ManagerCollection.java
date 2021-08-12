package com.workers.employees.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "managers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerCollection {
    @XmlElement(name = "manager")
    List<ManagerDto> managers;

    public ManagerCollection() {
    }

    public ManagerCollection(List<ManagerDto> managers) {
        this.managers = managers;
    }

    public List<ManagerDto> getManagers() {
        return managers;
    }

    public void setManagers(List<ManagerDto> managers) {
        this.managers = managers;
    }
}
