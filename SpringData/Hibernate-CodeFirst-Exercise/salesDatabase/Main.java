package salesDatabase;

import gringottsDatabase.WizardDeposits;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("sales_db");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = new Customer();
        customer.setName("Steve");
        customer.setCreditCardNumber("Bg1234");
        customer.setEmail("steveSt@mail.bg");

        Product product = new Product();
        product.setName("Coffee");
        product.setPrice(new BigDecimal("12.30"));
        product.setQuantity(1.5);

        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setName("JavaMart");

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setProduct(product);
        sale.setStoreLocation(storeLocation);
        sale.setDateTime(LocalDateTime.now());


        entityManager.persist(sale);
        transaction.commit();




    }
}
