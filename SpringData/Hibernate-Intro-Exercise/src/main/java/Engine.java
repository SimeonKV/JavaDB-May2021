import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private final Scanner scanner;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        // changeCasing();
        //checkIfEmployeeIsContained();
        // employeesWithSalaryOver50000();
        // employeesFromDepartment();
        //addNewAddressAndUpdateEmployee();
        //getEmployeeAddByEnteringTheirFirstAndLastName();
        //addressesWithEmployeeCount();
        //employeeWithProjects();
        //findLatest10Projects();
        //increaseSalaries();
        //findEmployeesByFirstName();
        // maxSalaryByDepartment();
        removeTowns();

    }

    private void removeTowns() {
        System.out.println("Enter town name: ");
        String deleteTown = scanner.nextLine();

        this.entityManager.getTransaction().begin();

        Town townToDelete =
                this.entityManager
                .createQuery("SELECT t FROM Town  t " +
                        "WHERE t.name = :townToDeleteName",Town.class)
                .setParameter("townToDeleteName",deleteTown)
                .getSingleResult();

        int townToDeleteId = this.entityManager
                .createQuery("UPDATE Address a" +
                        " SET a.town = null " +
                        "WHERE a.town.id = :townToDeleteId")
                .setParameter("townToDeleteId", townToDelete.getId())
                .executeUpdate();

        System.out.println(String.format("%d address in %s deleted",townToDeleteId,deleteTown));

        this.entityManager.remove(townToDelete);


        this.entityManager.getTransaction().commit();


    }

    private void maxSalaryByDepartment() {
        List<Object[]> depsWithMaxSalary = this.entityManager
                .createQuery("SELECT e.department.name, MAX(e.salary) FROM Employee  e " +
                        " GROUP BY e.department.name", Object[].class)
                .getResultList();

        for(Object[] department : depsWithMaxSalary){
            String depName = (String) department[0];
            BigDecimal maxSalary = (BigDecimal) department[1];
            System.out.println(depName + " -> " + maxSalary);

        }


    }

    private void findEmployeesByFirstName() {
        System.out.println("Enter characters");
        String chars = scanner.nextLine();


        this.entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.firstName LIKE concat(:char,'%') ",Employee.class)
                .setParameter("char",chars)
                .getResultStream()
                .forEach(employee ->
                        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " "
                        + employee.getSalary()));

    }

    private void increaseSalaries() {
        this.entityManager.getTransaction().begin();

         this.entityManager
                .createQuery("UPDATE Employee e " +
                        "SET e.salary = e.salary + (e.salary * 0.12) " +
                        "WHERE e.department.id IN(1,2,4)")
                .executeUpdate();

        this.entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.id IN(1,2,4)",Employee.class)
        .getResultStream()
        .forEach(employee -> System.out.println(employee.getFirstName() + " " + employee.getLastName() +
        " -> " + employee.getSalary()));


        this.entityManager.getTransaction().commit();
    }

    private void findLatest10Projects() {
        List<Project> projects = this.entityManager
                .createQuery("SELECT p FROM Project p " +
                        " ORDER BY p.startDate DESC, p.name ASC", Project.class)
                .setMaxResults(10)
                .getResultList();


        for(Project project : projects){
          LocalDateTime localDateTime = project.getStartDate();
          DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = localDateTime.format(dateTimeFormatter);


            System.out.println("Project name: " + project.getName());
            System.out.println("\tProject Description: " + project.getDescription());
            System.out.println("\tProject Start Date: " + formattedDate);



        }


    }

    private void employeeWithProjects() {
        System.out.println("Enter employee id: ");
        String id = scanner.nextLine();

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.id = :enteredId", Employee.class)
                .setParameter("enteredId",Integer.parseInt(id))
                .getSingleResult();

        System.out.println(String.format("%s %s - %s", employee.getFirstName(), employee.getLastName()
                , employee.getJobTitle()));

        Set<Project> projects = employee.getProjects();

        if(projects.isEmpty()){
            System.out.println("Employee has no projects");
        }else{
            projects.stream().sorted(Comparator.comparing(Project::getName))
                    .forEach(project -> System.out.println(project.getName()));
        }


    }

    private void addressesWithEmployeeCount() {
        List<Address> addresses = this.entityManager
                .createQuery("SELECT a FROM Address a " +
                        "ORDER BY a.employees.size DESC",Address.class)
                .setMaxResults(10)
                .getResultList();

        for(Address address : addresses){

            System.out.println(String.format("%d %s - %d employees",
                    address.getId(),address.getText(),address.getEmployees().size()));

        }


    }

    private void getEmployeeAddByEnteringTheirFirstAndLastName() {
        System.out.println("Enter employee first name: ");
        String fName = scanner.nextLine();
        System.out.println("Enter employee last name: ");
        String lName = scanner.nextLine();

        Employee employee = (Employee)
                this.entityManager
                .createQuery("SELECT e FROM Employee  e " +
                        "WHERE e.firstName = :fN AND e.lastName = :ln")
                .setParameter("fN",fName)
                .setParameter("ln",lName)
                .getSingleResult();


        System.out.println(employee.getFirstName() + " " + employee.getLastName() +
                " lives in " + employee.getAddress().getTown().getName());
    }

    private void addNewAddressAndUpdateEmployee() {
        String lastName = scanner.nextLine();
        Address address = createNewAddress();

        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        this.entityManager.createQuery("UPDATE Employee e " +
                " SET e.address.id = :newAddressId  " +
                " WHERE e.lastName = :lastNameEntered")
                .setParameter("newAddressId", address.getId())
                .setParameter("lastNameEntered", lastName)
                .executeUpdate();

        transaction.commit();


    }

    private Address createNewAddress() {
        Address address = new Address();
        address.setText("Vitoshka 15");

        this.entityManager.getTransaction().begin();

        Town sofia = this.entityManager.createQuery("SELECT t FROM Town t " +
                "WHERE t.id = 32", Town.class)
                .getSingleResult();
        address.setTown(sofia);

        this.entityManager.persist(address);


        this.entityManager.getTransaction().commit();

        return address;
    }

    private void employeesFromDepartment() {
        List<Employee> employees =
                this.entityManager
                        .createQuery("SELECT e FROM Employee e " +
                                "WHERE e.department.name = 'Research and Development' " +
                                "ORDER BY e.salary, e.id ", Employee.class)
                        .getResultList();

        employees.stream().forEach(employee -> System.out.println(String.format("%s %s from %s - $%.2f",
                employee.getFirstName(), employee.getLastName(),
                employee.getDepartment().getName(), employee.getSalary())));

    }

    private void employeesWithSalaryOver50000() {
        List<String> employees =
                this.entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.salary > 50000", Employee.class)
                        .getResultStream()
                        .map(employee -> employee.getFirstName())
                        .collect(Collectors.toList());

        employees.forEach(System.out::println);


    }

    private void checkIfEmployeeIsContained() {
        String enterName = scanner.nextLine();

        List<Employee> employeeContained = this.entityManager
                .createQuery("SELECT e FROM Employee e" +
                        " WHERE concat(e.firstName,' ',e.lastName) = :providedName", Employee.class)
                .setParameter("providedName", enterName)
                .getResultList();

        if (employeeContained.isEmpty()) {
            System.out.println("No such name");
        } else {
            System.out.println("Name " + enterName + " is contained");
        }


    }

    private void changeCasing() {
        List<Town> towns =
                this.entityManager
                        .createQuery("SELECT t FROM Town t " +
                                "WHERE LENGTH(t.name) <= 5  ", Town.class)
                        .getResultList();

        this.entityManager.getTransaction().begin();

        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }

        this.entityManager.getTransaction().commit();


    }
}
