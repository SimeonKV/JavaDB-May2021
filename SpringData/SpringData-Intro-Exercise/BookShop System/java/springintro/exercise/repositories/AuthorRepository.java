package springintro.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springintro.exercise.enteties.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {


}
