package universitySystem.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name",nullable = false,length = 30)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 30)
    private String lastName;
    @Column(name = "phone_number",length = 10)
    private String phoneNumber;
    @Column(name = "salary_per_hour",columnDefinition = "Decimal(5,2)")
    private BigDecimal salaryPerHour;
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();

    public Teacher() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
