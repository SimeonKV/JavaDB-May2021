package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softuni.exam.models.entity.Car;


public interface CarRepository extends JpaRepository<Car,Long> {

}
