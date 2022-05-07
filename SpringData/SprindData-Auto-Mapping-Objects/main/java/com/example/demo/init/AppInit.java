package com.example.demo.init;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ManagerDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Employee;
import com.example.demo.sevice.AddressService;
import com.example.demo.sevice.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppInit implements CommandLineRunner {
    private final EmployeeService employeeService;
    private final AddressService addressService;

    public AppInit(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

//        List<Address> addresses = List.of(
//                new Address("Bulgaria","Sofia","ul. G.Rakovski, 50"),
//                new Address("Bulgaria","Sofia","bul. Dondukov, 45"),
//                new Address("Bulgaria","Sofia","ul. Hristo Smirnenski, 40"),
//                new Address("Bulgaria","Sofia","bul. Aleksandar Malinov, 93"),
//                new Address("Bulgaria","Sofia","bul. Slivnitsa, 50"),
//                new Address("Bulgaria","Sofia","ul. Aleksandar Kunchev, 1")
//        );
//
//        addresses = addresses.stream().map(addressService::addAddress).collect(Collectors.toList());
//
//        List<Employee> employees = List.of(
//                new Employee("Steve","Adams",5780,
//                        LocalDate.of(1982,3,12),addresses.get(0)),
//
//                new Employee("Stephen","Petrov",2760
//                        ,LocalDate.of(1974,5,19),addresses.get(1)),
//
//                new Employee("Hristina","Petrova",3680
//                        ,LocalDate.of(1989,1,3),addresses.get(1)),
//
//                new Employee("Diana","Atanasova",6790
//                        ,LocalDate.of(1989,12,13),addresses.get(2)),
//
//                new Employee("Samuil","Georgiev",4780,
//                        LocalDate.of(1979,2,22),addresses.get(3)),
//
//                new Employee("Ivan","Petrov",3780,
//                        LocalDate.of(1990,11,10),addresses.get(4)),
//
//                new Employee("Georgi","Petrov",3966,
//                        LocalDate.of(1988,6,17),addresses.get(5))
//        );
//
//        List<Employee> created = employees.stream()
//                .map(this.employeeService::addEmployee).collect(Collectors.toList());
//
//        created.get(1).setManager(created.get(0));
//        created.get(2).setManager(created.get(0));
//
//        created.get(4).setManager(created.get(3));
//        created.get(5).setManager(created.get(3));
//        created.get(6).setManager(created.get(3));
//
//        created.stream().map(employee -> this.employeeService.updateEmployee(employee)).collect(Collectors.toList());



        TypeMap<Employee, ManagerDTO> typeMap = modelMapper
                .createTypeMap(Employee.class,ManagerDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getSubordinates(),ManagerDTO::setEmployees);
                });

    List<Employee> managers = this.employeeService.getAllManagers();
    List<ManagerDTO> managerDTOS = managers.stream()
            .map(eachManager -> typeMap.map(eachManager))
            .collect(Collectors.toList());

        managerDTOS.forEach(m -> System.out.println(m.toString()));

    }
}
