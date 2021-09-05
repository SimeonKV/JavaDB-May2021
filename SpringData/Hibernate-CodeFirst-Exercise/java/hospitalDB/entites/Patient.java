package hospitalDB.entites;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name",nullable = false,length = 20)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 20)
    private String lastName;
    @Column(nullable = false,length = 30)
    private String address;
    private String email;
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;
    @Column(name = "medical_insurance",nullable = false)
    private Boolean medicalInsurance;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Boolean medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }
}
