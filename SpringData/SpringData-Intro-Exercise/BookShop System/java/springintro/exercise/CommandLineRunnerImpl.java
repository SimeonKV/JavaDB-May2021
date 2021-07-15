package springintro.exercise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springintro.exercise.enteties.Book;
import springintro.exercise.services.AuthorService;
import springintro.exercise.services.BookService;
import springintro.exercise.services.CategoryService;

import java.io.IOException;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //writeDataToDbFromTxtFiles();
        //this.bookService.extractAllBooksAfterYear(2000);
        //this.bookService.extractAllBooksBefore1990();
        //this.bookService.extractAuthorsWithBooksCount();
        this.bookService.extractAllBooksByGeorgePowell();

    }



    private void writeDataToDbFromTxtFiles() throws IOException {
        categoryService.writeData();
        authorService.writeData();
        bookService.writeData();
    }
}
