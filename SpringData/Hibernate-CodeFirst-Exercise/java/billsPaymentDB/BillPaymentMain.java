package billsPaymentDB;

import billsPaymentDB.entites.BankAccount;
import billsPaymentDB.entites.CreditCard;
import billsPaymentDB.entites.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BillPaymentMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("bill_payment_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User user = new User();
        user.setFirstName("Simo");
        user.setLastName("Simov");
        user.setEmail("SimoBg@yahoo.com");
        user.setPassword("easyPassWord$$$");

        CreditCard creditCard = new CreditCard();
        creditCard.setType("Gold");
        creditCard.setNumber("267109");
        creditCard.setExpirationYear(2023);
        creditCard.setExpirationMonth(12);
        creditCard.setUser(user);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumber("900845");
        bankAccount.setSwiftCode("123789");
        bankAccount.setName("Savings");
        bankAccount.setUser(user);

        User userTwo = new User();
        userTwo.setFirstName("Georgi");
        userTwo.setLastName("Dimitrov");
        userTwo.setEmail("DimitrovG@abv.bg");
        userTwo.setPassword("easy$$$");

        CreditCard creditCardTwo = new CreditCard();
        creditCardTwo.setType("Silver");
        creditCardTwo.setNumber("456890");
        creditCardTwo.setExpirationMonth(8);
        creditCardTwo.setExpirationYear(2022);
        creditCardTwo.setUser(userTwo);

        entityManager.persist(creditCard);
        entityManager.persist(creditCardTwo);
        entityManager.persist(bankAccount);




        transaction.commit();




    }
}
