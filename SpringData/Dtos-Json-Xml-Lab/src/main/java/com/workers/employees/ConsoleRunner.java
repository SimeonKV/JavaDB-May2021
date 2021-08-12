package com.workers.employees;

import com.google.gson.Gson;
import com.workers.employees.dto.EmployeeCreateRequestDto;
import com.workers.employees.dto.EmployeeCreateResponseDto;
import com.workers.employees.dto.ManagerCollection;
import com.workers.employees.dto.ManagerDto;
import com.workers.employees.services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final EmployeeService employeeService;
    private final Gson gson;

    public ConsoleRunner(EmployeeService employeeService, Gson gson) {
        this.employeeService = employeeService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(ManagerDto.class, ManagerCollection.class,
                EmployeeCreateRequestDto.class,EmployeeCreateResponseDto.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        while(!line.equals("end")){
            String[] cmdParts = line.split(" ",2);

            switch (cmdParts[0]){
                case "find":
                    long id = Long.parseLong(cmdParts[1]);
                    ManagerDto managerDto = this.employeeService.findManger(id);
                    String mangerToJson = this.gson.toJson(managerDto);
                    System.out.println(mangerToJson);

                    marshaller.marshal(managerDto,System.out);
                    break;
                case"findAll":
                    List<ManagerDto> managers = this.employeeService.findAllManagers();
                    String managersToJson = this.gson.toJson(managers);
                    System.out.println(managersToJson);

                    marshaller.marshal(new ManagerCollection(managers),System.out);

                     break;
                case"save":
                    String json = cmdParts[1];
//                    EmployeeCreateRequestDto employeeCreateRequestDto =
//                            this.gson.fromJson(json, EmployeeCreateRequestDto.class);
//
//                    EmployeeCreateResponseDto response = this.employeeService.save(employeeCreateRequestDto);
//                    System.out.println(this.gson.toJson(response));

             EmployeeCreateRequestDto employeeCreateRequestDto =
                     (EmployeeCreateRequestDto)   unmarshaller.unmarshal(new ByteArrayInputStream(json.getBytes()));

                    EmployeeCreateResponseDto employeeCreateResponseDto = this.employeeService.save(employeeCreateRequestDto);
                    marshaller.marshal(employeeCreateResponseDto,System.out);

                    System.out.println();

                    break;
                case"save-from-file":
//                    Path filePath = Path.of("src\\main\\resources\\first-employee.json");
//                    String jsonRead = Files.readString(filePath);
//                    EmployeeCreateRequestDto request = this.gson.fromJson(jsonRead,EmployeeCreateRequestDto.class);
//                    EmployeeCreateResponseDto responseDto = this.employeeService.save(request);
//                    System.out.println(responseDto);

                    File filePath =
                         new File("src\\main\\resources\\newEmployee.xml");
                    EmployeeCreateRequestDto request = (EmployeeCreateRequestDto)
                            unmarshaller.unmarshal(filePath);

                    EmployeeCreateResponseDto response = this.employeeService.save(request);

                    marshaller.marshal(request,System.out);


                    break;
                case"file-to":
                    List<ManagerDto> allManagers = this.employeeService.findAllManagers();
                    FileWriter fileWriter = new FileWriter("src\\main\\resources\\allManagers.json");

                    this.gson.toJson(allManagers,fileWriter);

                    fileWriter.flush();
                    fileWriter.close();
                    break;

            }

            line = scanner.nextLine();
        }


    }
}
