package xml.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xml.exercise.model.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u " +
            "WHERE (SELECT count(p) FROM Product  p WHERE p.seller.id = u.id AND p.buyer IS NOT NULL) > 0 " +
             "ORDER BY u.lastName,u.firstName")
    List<User> findAllUsersWithMoreThanOneSoldProduct();
}
