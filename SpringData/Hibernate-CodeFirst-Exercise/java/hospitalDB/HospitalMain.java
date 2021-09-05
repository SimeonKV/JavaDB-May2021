package hospitalDB;

import hospitalDB.entites.Diagnose;
import hospitalDB.entites.Medicament;
import hospitalDB.entites.Patient;
import hospitalDB.entites.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HospitalMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("hospital_db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Patient patient = new Patient();
        patient.setFirstName("Georgi");
        patient.setLastName("Dimitrov");
        patient.setEmail("georgiDimi@abv.bg");
        patient.setAddress("Sandanski");
        patient.setMedicalInsurance(true);
        patient.setBirthDate( LocalDate.of(1985,4,23));

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Flu");
        diagnose.setComment("Flu with mild cough and temperature");

        Medicament medicament = new Medicament();
        medicament.setName("Analgin");

        Medicament medicamentTwo = new Medicament();
        medicamentTwo.setName("Vitamin C");

        Visitation visitation = finishVisitation(patient,diagnose,medicament,medicamentTwo);

        Patient patientTwo = new Patient();
        patientTwo.setFirstName("Dimitar");
        patientTwo.setLastName("Georgiev");
        patientTwo.setEmail("mitko07@abv.bg");
        patientTwo.setAddress("Silistra");
        patientTwo.setMedicalInsurance(false);
        patientTwo.setBirthDate(LocalDate.of(1990,11,10));

        Diagnose diagnoseTwo = new Diagnose();
        diagnoseTwo.setName("Covid19");
        diagnoseTwo.setComment("Virus with mild to severe temperature, headache, lack of energy in your whole body");

        Medicament medicamentThree = new Medicament();
        medicamentThree.setName("Paracetamol");

        Medicament medicamentFour = new Medicament();
        medicamentFour.setName("Aspirin");

        Visitation visitationTwo = finishVisitation(patientTwo,diagnoseTwo,medicamentThree,medicamentFour);

        entityManager.persist(visitation);
        entityManager.persist(visitationTwo);

        transaction.commit();


    }

    private static Visitation finishVisitation(Patient patient, Diagnose diagnose, Medicament... medicaments) {
       Visitation visitation = new Visitation();
       visitation.setTime(LocalDateTime.now());
       visitation.completeVisitation(patient,diagnose,medicaments);



       return visitation;
    }
}
