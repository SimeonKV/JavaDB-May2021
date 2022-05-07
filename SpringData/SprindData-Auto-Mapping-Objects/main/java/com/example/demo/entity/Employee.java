package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private double salary;
    @NonNull
    private LocalDate birthday;
    @ManyToOne
    @NonNull
    private Address address;
    private boolean onVacation;
    @ManyToOne(optional = true)
    @JoinColumn(name = "manager_id")
    private Employee manager;
    @OneToMany(mappedBy = "manager",fetch = FetchType.EAGER)
    private List<Employee> subordinates = new ArrayList<>();

}
