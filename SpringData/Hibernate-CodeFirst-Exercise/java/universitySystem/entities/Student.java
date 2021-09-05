package universitySystem.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name",nullable = false,length = 30)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 30)
    private String lastName;
    @Column(name = "phone_number",length = 10)
    private String phoneNumber;
    @Column(name = "average_grade",columnDefinition = "DECIMAL(3,2)")
    private Double averageGrade;
    private int attendance;
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();


    public Student() {
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

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return attendance == student.attendance &&
                Objects.equals(id, student.id) &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(phoneNumber, student.phoneNumber) &&
                Objects.equals(averageGrade, student.averageGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, averageGrade, attendance);
    }
}
