package gringottsDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("gringotts-deposits");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        WizardDeposits wizardDeposits = new WizardDeposits();
        wizardDeposits.setFirstName("Simo");
        wizardDeposits.setLastName("Simov");
        wizardDeposits.setAge(10);
        wizardDeposits.setMagicWandCreator("xxx");
        wizardDeposits.setMagicWandSize(25);
        wizardDeposits.setDepositGroup("Group1");
        wizardDeposits.setDepositStartDate(LocalDateTime.now());
        wizardDeposits.setDepositAmount(new BigDecimal("10"));
        wizardDeposits.setDepositInterest(new BigDecimal("12.3"));
        wizardDeposits.setDepositCharge(new BigDecimal("7.78"));
        wizardDeposits.setDepositExpirationDate(Main.plusFifteenDays(LocalDateTime.now()));
        wizardDeposits.setDepositExpired(false);

        entityManager.persist(wizardDeposits);
        transaction.commit();
    }

    public static LocalDateTime plusFifteenDays(LocalDateTime ldt){
        LocalDateTime localDateTime = ldt.plusDays(15);

        return localDateTime;
    }
}
