import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HibernateIntroMain {


    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-code");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Student student = new Student("Hristo","Georgiev");
        entityManager.persist(student);
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        System.out.println(student.toString());
        entityTransaction.commit();
        entityManager.close();


//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//
//            System.out.println("These app has these options");
//            System.out.println("For you to save a student press --> 1");
//            System.out.println("For you to get a student from repository press --> 2");
//            System.out.println("For you to get all students from repository press --> 3");
//            System.out.println("For you to get a student by Full Name from repository press --> 4");
//            System.out.println("For you to get a student by Id from repository press --> 5");
//            System.out.println("Criteria Builder press --> 6");
//            System.out.println("For you to get names starting with letters X and Y press --> 7");
//            String line = scanner.nextLine();
//
//            if (line.equalsIgnoreCase("end")) {
//                return;
//            }
//
//            switch (line) {
//                case "1":
//                    System.out.println("Enter first name");
//                    String firstName = scanner.nextLine();
//                    System.out.println("Enter last name");
//                    String lastName = scanner.nextLine();
//                    saveStudent(firstName, lastName);
//                    break;
//                case "2":
//                    System.out.println("Enter valid id");
//                    String id = scanner.nextLine();
//                    printStudentById(id);
//                    break;
//                case "3":
//                    printAllStudents();
//                    break;
//                case "4":
//                    System.out.println("Enter first name of the student");
//                    String fName = scanner.nextLine();
//                    System.out.println("Enter last name of the student");
//                    String lName = scanner.nextLine();
//                    printStudentByName(fName, lName);
//                    break;
//                case "5":
//                    System.out.println("Enter id of student");
//                    String idOfStudent = scanner.nextLine();
//                    printStudentSearchedById(idOfStudent);
//                case "6":
//                    criteriaBuilderDemo(1l);
//                case "7":
//                    System.out.println("Enter first letter of first name");
//                    String firstLetterFirstName = scanner.nextLine();
//                    System.out.println("Enter first letter of second name");
//                    String firstLetterSecondName = scanner.nextLine();
//                    nameStartsWithLetterXOrLetterY(firstLetterFirstName, firstLetterSecondName);
//                    break;
//            }
//
//
//            while (true) {
//
//                System.out.println("To end the execution of the program type in the word --> end");
//                System.out.println("To continue type in the word --> continue");
//                String command = scanner.nextLine();
//
//                if (command.equalsIgnoreCase("continue")) {
//                    break;
//                } else if (command.equalsIgnoreCase("end")) {
//                    return;
//                }
//            }
//        }
//

    }

//    private static void nameStartsWithLetterXOrLetterY(String firstLetterFirstName, String firstLetterSecondName) {
//        //SessionFactory sessionFactory = cfg.buildSessionFactory();
//      //  Session session = sessionFactory.openSession();
//      //  Transaction transaction = session.beginTransaction();
//
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
//        Root<Student> studentRoot = criteriaQuery.from(Student.class);
//
//        criteriaQuery.select(studentRoot);
//
//        Predicate firstNameStartsWith = criteriaBuilder.like(studentRoot.get("name"),firstLetterFirstName.toUpperCase()+"%");
//        Predicate secondNameStartsWith = criteriaBuilder.like(studentRoot.get("name"),firstLetterSecondName.toUpperCase()+"%");
//
//        Predicate or = criteriaBuilder.or(firstNameStartsWith,secondNameStartsWith);
//        criteriaQuery.where(or);
//
//        List<Student> students = session.createQuery(criteriaQuery).getResultList();
//
//        //students.forEach(student -> System.out.println(student.getName()));
//
//
//        transaction.commit();
//        session.close();
//
//    }
//
//    private static void criteriaBuilderDemo(long id) {
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        //Create criteria builder
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//
//        //Create criteria query
//        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
//
//        Root<Student> studentRoot = criteriaQuery.from(Student.class);
//
//        criteriaQuery.select(studentRoot);
//
//        criteriaQuery.where(criteriaBuilder.equal(studentRoot.get("id"), id));
//        TypedQuery<Student> typedQuery = session.createQuery(criteriaQuery);
//        Student student = typedQuery.getSingleResult();
//        //System.out.println(student.getName());
//
//
//        transaction.commit();
//        session.close();
//
//
//    }
//
//    private static void printStudentSearchedById(String idOfStudent) {
//        Long id = Long.parseLong(idOfStudent);
//
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Student student = (Student) session
//                .createQuery("FROM entity.Student WHERE id = :studentId")
//                .setParameter("studentId", id)
//                .getSingleResult();
//
//        //System.out.println(student.getName());
//
//        transaction.commit();
//        session.close();
//
//    }
//
//    private static void printStudentByName(String fName, String lName) {
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        String fullName = fName + " " + lName;
//
//        Student firstResult = (Student) session
//                .createQuery("FROM entity.Student WHERE name = :fuName")
//                .setParameter("fuName", fullName)
//                .getSingleResult();
//
//       // System.out.println(firstResult.getName());
//
//        transaction.commit();
//
//    }
//
//    public static void printAllStudents() {
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        List<Student> students = session.createQuery("FROM entity.Student ", Student.class).list();
//
//        //students.forEach(student -> System.out.println(student.getName()));
//
//        transaction.commit();
//        session.close();
//
//    }
//
//    public static void printStudentById(String id) {
//        Long idAsLong = Long.parseLong(id);
//
//
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Optional<Student> student = session.byId(Student.class).loadOptional(idAsLong);
//
//        if (student.isPresent()) {
//            Student studentPresent = student.get();
//        //    System.out.println("Student " + studentPresent.getName() + " with id " + idAsLong);
//        } else {
//            System.out.println("No existing student with this id");
//        }
//
//        transaction.commit();
//        session.close();
//
//    }
//
//    public static void saveStudent(String firstName, String lastName) {
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        String fullName = firstName + " " + lastName;
//       // Student student = new Student(fullName);
//       // session.save(student);
//
//        transaction.commit();
//        session.close();
//    }


}
