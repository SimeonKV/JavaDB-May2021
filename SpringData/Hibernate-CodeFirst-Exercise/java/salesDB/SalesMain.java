package salesDB;

import javax.persistence.*;

public class SalesMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("sales_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        System.out.println("Sales DB created successfully!");

        entityTransaction.commit();


    }
}
