package universitySystem.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 20)
    private String name;
    @Column(columnDefinition = "Text")
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private Integer credits;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;


    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public void addStudent(Student student){
        this.students.add(student);
        student.getCourses().add(this);
    }

    public void removeStudent(Student student){
        student.getCourses().remove(this);
        this.students.remove(student);
    }

    public void addTeacher(Teacher teacher){
        this.teacher = teacher;
        this.teacher.getCourses().add(this);
    }

    public void removeTeacher(Teacher teacher){
        teacher.getCourses().remove(this);
        this.teacher = null;
    }

}
