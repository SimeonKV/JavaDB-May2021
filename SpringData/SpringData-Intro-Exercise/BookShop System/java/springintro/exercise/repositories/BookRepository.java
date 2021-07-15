package springintro.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springintro.exercise.enteties.Author;
import springintro.exercise.enteties.Book;

import javax.print.attribute.standard.JobKOctets;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE b.releaseDate > :providedYear ORDER BY b.releaseDate")
    List<Book> getAllBooksAfterYear(@Param("providedYear")LocalDate localDate);

    @Query("SELECT a FROM Book b JOIN b.author a WHERE b.releaseDate < :providedYear")
    Set<Author> getAllAuthorsWithBookReleaseDateBefore(@Param("providedYear") LocalDate year);

    @Query("SELECT a.firstName,a.lastName,COUNT(b.id) AS bookCount FROM Book b JOIN b.author a" +
            " GROUP BY b.author.id ORDER BY bookCount desc")
    List<List<Object>> getAllAuthorsOrderByTheirBookCountInDesc();

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastName(String firstName,String lastName);



}
