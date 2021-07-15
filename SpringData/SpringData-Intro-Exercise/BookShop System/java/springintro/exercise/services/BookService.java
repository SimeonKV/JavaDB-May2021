package springintro.exercise.services;

import springintro.exercise.enteties.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void writeData() throws IOException;
    void extractAllBooksAfterYear(int year);
    void extractAllBooksBefore1990();
    void extractAuthorsWithBooksCount();
    void extractAllBooksByGeorgePowell();


}
