package universitySystem;

import universitySystem.entities.Course;
import universitySystem.entities.Student;
import universitySystem.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class UniversitySystemMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("university_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Course course = createCourse();
        entityManager.persist(course);


        transaction.commit();



    }

    private static Course createCourse() {
        Student student = new Student();
        student.setFirstName("Simeon");
        student.setLastName("Krasimirov");
        student.setAttendance(10);
        student.setAverageGrade(6d);


        Student studentTwo = new Student();
        studentTwo.setFirstName("George");
        studentTwo.setLastName("George");
        studentTwo.setAttendance(10);
        studentTwo.setAverageGrade(5.60);


        Student studentThree = new Student();
        studentThree.setFirstName("Maria");
        studentThree.setLastName("Doe");
        studentThree.setAttendance(10);
        studentThree.setAverageGrade(5.50);

        Teacher teacher = new Teacher();
        teacher.setFirstName("Dimitar");
        teacher.setLastName("Dimitrov");
        teacher.setPhoneNumber("088888");
        teacher.setSalaryPerHour(new BigDecimal(10.50));

        Course course = new Course();
        course.setName("Java Database");
        course.setCredits(15);
        course.setDescription("Learning how to connect data into DB through Java");

        course.addStudent(student);
        course.addStudent(studentTwo);
        course.addStudent(studentThree);
        course.addTeacher(teacher);

        return course;
    }
}
